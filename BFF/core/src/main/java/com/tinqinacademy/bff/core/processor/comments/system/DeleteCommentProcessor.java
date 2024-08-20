package com.tinqinacademy.bff.core.processor.comments.system;

import com.tinqinacademy.bff.api.operations.comments.deletecommentbyadmin.DeleteCommentOperation;
import com.tinqinacademy.bff.api.operations.comments.deletecommentbyadmin.DeleteCommentRequest;
import com.tinqinacademy.bff.api.operations.comments.deletecommentbyadmin.DeleteCommentResponse;
import com.tinqinacademy.bff.core.errorhandler.ErrorHandler;
import com.tinqinacademy.bff.core.processor.base.BaseOperationProcessor;
import com.tinqinacademy.bff.api.models.errors.ErrorWrapper;
import com.tinqinacademy.comments.restexport.CommentsClient;
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

    private final CommentsClient commentsClient;

    protected DeleteCommentProcessor(ConversionService conversionService, Validator validator, ErrorHandler errorHandler, CommentsClient commentsClient) {
        super(conversionService, validator, errorHandler);
        this.commentsClient = commentsClient;
    }


    @Override
    public Either<ErrorWrapper, DeleteCommentResponse> process(DeleteCommentRequest input) {
        return Try.of(() -> {
                    log.info("Start deleteComment with input: {}", input);
                    validateInput(input);
                    commentsClient.deleteComment(input.getCommendId());
                    DeleteCommentResponse output = DeleteCommentResponse.builder().build();
                    log.info("End deleteComment with output: {}", output);
                    return output;
                })
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }

}
