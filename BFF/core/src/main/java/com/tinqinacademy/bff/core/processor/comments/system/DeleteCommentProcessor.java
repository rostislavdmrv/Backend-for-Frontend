package com.tinqinacademy.bff.core.processor.comments.system;

import com.tinqinacademy.bff.api.operations.comments.deletecommentbyadmin.DeleteCommentOperation;
import com.tinqinacademy.bff.api.operations.comments.deletecommentbyadmin.DeleteCommentRequest;
import com.tinqinacademy.bff.api.operations.comments.deletecommentbyadmin.DeleteCommentResponse;
import com.tinqinacademy.bff.core.errorhandler.ErrorHandler;
import com.tinqinacademy.bff.core.processor.base.BaseOperationProcessor;
import com.tinqinacademy.bff.persistence.models.entities.comments.Comment;
import com.tinqinacademy.bff.persistence.repositories.comments.CommentRepository;
import com.tinqinacademy.comments.api.exceptions.ResourceNotFoundException;
import com.tinqinacademy.bff.api.models.errors.ErrorWrapper;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@Slf4j
public class DeleteCommentProcessor extends BaseOperationProcessor<DeleteCommentRequest, DeleteCommentResponse> implements DeleteCommentOperation {

    private final CommentRepository commentRepository;
    protected DeleteCommentProcessor(ConversionService conversionService, Validator validator, ErrorHandler errorHandler, CommentRepository commentRepository) {
        super(conversionService, validator, errorHandler);
        this.commentRepository = commentRepository;
    }


    @Override
    public Either<ErrorWrapper, DeleteCommentResponse> process(DeleteCommentRequest input) {
        log.info("Start deleting whole comment by admin");

        return Try.of(() -> {
                    Comment comment = retrieveComment(input.getCommendId());
                    deleteComment(comment);
                    DeleteCommentResponse output = buildDeleteCommentOutput();
                    log.info("Ended: Comment with ID {} has been deleted", comment.getId());
                    return output;
                })
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }
    private Comment retrieveComment(String commentId) {
        UUID id = UUID.fromString(commentId);
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", id.toString()));
    }

    private void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }

    private DeleteCommentResponse buildDeleteCommentOutput() {
        return DeleteCommentResponse.builder().build();
    }
}
