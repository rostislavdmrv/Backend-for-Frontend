package com.tinqinacademy.bff.core.processor.comments.system;


import com.tinqinacademy.bff.api.models.errors.ErrorWrapper;
import com.tinqinacademy.bff.api.operations.comments.editcommentallbyadmin.EditCommentAllRequest;
import com.tinqinacademy.bff.api.operations.comments.editcommentallbyadmin.EditCommentAllResponse;
import com.tinqinacademy.bff.api.operations.comments.editcommentallbyadmin.EditCommentWholeOperation;
import com.tinqinacademy.bff.core.errorhandler.ErrorHandler;
import com.tinqinacademy.bff.core.processor.base.BaseOperationProcessor;
import com.tinqinacademy.bff.persistence.models.entities.comments.Comment;
import com.tinqinacademy.bff.persistence.repositories.comments.CommentRepository;
import com.tinqinacademy.comments.api.exceptions.ResourceNotFoundException;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class UpdateCommentProcessor extends BaseOperationProcessor<EditCommentAllRequest, EditCommentAllResponse> implements EditCommentWholeOperation {

    private final CommentRepository commentRepository;

    protected UpdateCommentProcessor(ConversionService conversionService, Validator validator, ErrorHandler errorHandler, CommentRepository commentRepository) {
        super(conversionService, validator, errorHandler);
        this.commentRepository = commentRepository;
    }


    @Override
    public Either<ErrorWrapper, EditCommentAllResponse> process(EditCommentAllRequest input) {
        log.info("Start updating whole comment by admin");

        return Try.of(() -> {
                    Comment comment = retrieveComment(input.getCommentId());
                    updateCommentDetails(comment, input);
                    Comment updatedComment = saveComment(comment);

                    EditCommentAllResponse output = buildEditCommentAllOutput(updatedComment);

                    log.info("End updating whole comment by admin");
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

    private void updateCommentDetails(Comment comment, EditCommentAllRequest input) {
        comment.setFirstName(input.getFirstName());
        comment.setLastName(input.getLastName());
        comment.setContent(input.getContent());
        comment.setLastEditedDate(LocalDateTime.now());
        comment.setLastEditedBy(UUID.randomUUID());
    }

    private Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    private EditCommentAllResponse buildEditCommentAllOutput(Comment updatedComment) {
        return EditCommentAllResponse.builder()
                .id(updatedComment.getId().toString())
                .build();
    }


}
