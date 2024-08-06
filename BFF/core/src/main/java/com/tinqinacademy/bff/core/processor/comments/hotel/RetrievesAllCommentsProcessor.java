package com.tinqinacademy.bff.core.processor.comments.hotel;
import com.tinqinacademy.bff.api.models.errors.ErrorWrapper;
import com.tinqinacademy.bff.api.operations.comments.returnsallcommentsforcertainroom.CommentInfo;
import com.tinqinacademy.bff.api.operations.comments.returnsallcommentsforcertainroom.ReturnCommentOperation;
import com.tinqinacademy.bff.api.operations.comments.returnsallcommentsforcertainroom.ReturnCommentRequest;
import com.tinqinacademy.bff.api.operations.comments.returnsallcommentsforcertainroom.ReturnCommentResponse;
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

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class RetrievesAllCommentsProcessor extends BaseOperationProcessor<ReturnCommentRequest,ReturnCommentResponse> implements ReturnCommentOperation {

    private  final CommentRepository commentRepository;

    protected RetrievesAllCommentsProcessor(ConversionService conversionService, Validator validator, ErrorHandler errorHandler, CommentRepository commentRepository) {
        super(conversionService, validator, errorHandler);
        this.commentRepository = commentRepository;
    }


    @Override
    public Either<ErrorWrapper, ReturnCommentResponse> process(ReturnCommentRequest input) {
        log.info("Start retrieving all the comments for certain room");

        return Try.of(() -> {
                    List<Comment> comments = retrieveComments(input);
                    List<CommentInfo> commentInputs = convertCommentsToCommentInfo(comments);

                    ReturnCommentResponse output = ReturnCommentResponse.builder()
                            .comments(commentInputs)
                            .build();

                    log.info("End retrieving all the comments for certain room {}",output);
                    return output;

                })
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }
    private List<Comment> retrieveComments(ReturnCommentRequest input) {
        return commentRepository.findByRoomId(UUID.fromString(input.getRoomId()));
    }

    private List<CommentInfo> convertCommentsToCommentInfo(List<Comment> comments) {
        return comments.stream()
                .map(comment -> conversionService.convert(comment, CommentInfo.class))
                .toList();
    }

}
