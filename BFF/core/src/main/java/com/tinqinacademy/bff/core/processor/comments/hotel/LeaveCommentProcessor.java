package com.tinqinacademy.bff.core.processor.comments.hotel;


import com.tinqinacademy.bff.api.models.errors.ErrorWrapper;
import com.tinqinacademy.bff.api.operations.comments.leavescommentsforcertainroom.LeaveCommentOperation;
import com.tinqinacademy.bff.api.operations.comments.leavescommentsforcertainroom.LeaveCommentRequest;
import com.tinqinacademy.bff.api.operations.comments.leavescommentsforcertainroom.LeaveCommentResponse;
import com.tinqinacademy.bff.core.errorhandler.ErrorHandler;
import com.tinqinacademy.bff.core.processor.base.BaseOperationProcessor;
import com.tinqinacademy.bff.persistence.models.entities.comments.Comment;
import com.tinqinacademy.bff.persistence.repositories.comments.CommentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LeaveCommentProcessor extends BaseOperationProcessor<LeaveCommentRequest, LeaveCommentResponse> implements LeaveCommentOperation {

    private final CommentRepository commentRepository;

    protected LeaveCommentProcessor(ConversionService conversionService, Validator validator, ErrorHandler errorHandler, CommentRepository commentRepository) {
        super(conversionService, validator, errorHandler);
        this.commentRepository = commentRepository;
    }

    @Override
    public Either<ErrorWrapper, LeaveCommentResponse> process(LeaveCommentRequest input) {
        log.info("Start leaving comment");

        return Try.of(() -> {
                    Comment comment = convertInputToComment(input);
                    Comment savedComment = saveComment(comment);
                    LeaveCommentResponse output = buildLeaveCommentOutput(savedComment);
                    log.info("End leaving comment");
                    return output;
                })
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }

    private Comment convertInputToComment(LeaveCommentRequest input) {
        Comment comment = conversionService.convert(input, Comment.class);
        if (comment == null) {
            throw new IllegalStateException("Conversion of input to Comment failed");
        }
        return comment;
    }

    private Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    private LeaveCommentResponse buildLeaveCommentOutput(Comment savedComment) {
        return LeaveCommentResponse.builder()
                .id(savedComment.getId().toString())
                .build();
    }
}
