package com.tinqinacademy.bff.core.processor.comments.system;

import com.tinqinacademy.bff.api.operations.comments.deletecommentbyadmin.DeleteCommentOperation;
import com.tinqinacademy.bff.api.operations.comments.deletecommentbyadmin.DeleteCommentRequest;
import com.tinqinacademy.bff.api.operations.comments.deletecommentbyadmin.DeleteCommentResponse;
import com.tinqinacademy.bff.core.errorhandler.ErrorHandler;
import com.tinqinacademy.bff.core.processor.base.BaseOperationProcessor;
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


    protected DeleteCommentProcessor(ConversionService conversionService, Validator validator, ErrorHandler errorHandler) {
        super(conversionService, validator, errorHandler);
    }


    @Override
    public Either<ErrorWrapper, DeleteCommentResponse> process(DeleteCommentRequest input) {
        log.info("Start deleting whole comment by admin");

        return Try.of(() -> {

                    DeleteCommentResponse output = DeleteCommentResponse.builder().build();
                    log.info("Ended: Comment with ID {} has been deleted",output);
                    return output;
                })
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }

}
