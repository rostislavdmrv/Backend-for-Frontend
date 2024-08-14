package com.tinqinacademy.bff.core.processor.comments.system;
import com.tinqinacademy.bff.api.models.errors.ErrorWrapper;
import com.tinqinacademy.bff.api.operations.comments.editcommentallbyadmin.EditCommentAllRequest;
import com.tinqinacademy.bff.api.operations.comments.editcommentallbyadmin.EditCommentAllResponse;
import com.tinqinacademy.bff.api.operations.comments.editcommentallbyadmin.EditCommentWholeOperation;
import com.tinqinacademy.bff.core.errorhandler.ErrorHandler;
import com.tinqinacademy.bff.core.processor.base.BaseOperationProcessor;
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


    protected UpdateCommentProcessor(ConversionService conversionService, Validator validator, ErrorHandler errorHandler) {
        super(conversionService, validator, errorHandler);
    }


    @Override
    public Either<ErrorWrapper, EditCommentAllResponse> process(EditCommentAllRequest input) {
        log.info("Start updating whole comment by admin");

        return Try.of(() -> {


                    EditCommentAllResponse output = EditCommentAllResponse.builder().build();

                    log.info("End updating whole comment by admin");
                    return output;
                })
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }



}
