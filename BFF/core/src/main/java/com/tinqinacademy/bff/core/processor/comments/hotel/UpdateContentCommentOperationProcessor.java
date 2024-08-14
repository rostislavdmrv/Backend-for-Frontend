package com.tinqinacademy.bff.core.processor.comments.hotel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.bff.api.models.errors.ErrorWrapper;
import com.tinqinacademy.bff.api.operations.comments.editcommentbyuser.EditCommentContentOperation;
import com.tinqinacademy.bff.api.operations.comments.editcommentbyuser.EditCommentContentRequest;
import com.tinqinacademy.bff.api.operations.comments.editcommentbyuser.EditCommentContentResponse;
import com.tinqinacademy.bff.core.errorhandler.ErrorHandler;
import com.tinqinacademy.bff.core.processor.base.BaseOperationProcessor;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UpdateContentCommentOperationProcessor extends BaseOperationProcessor<EditCommentContentRequest, EditCommentContentResponse> implements EditCommentContentOperation {

    private final ObjectMapper objectMapper;


    protected UpdateContentCommentOperationProcessor(ConversionService conversionService, Validator validator, ErrorHandler errorHandler, ObjectMapper objectMapper) {
        super(conversionService, validator, errorHandler);
        this.objectMapper = objectMapper;
    }


    @Override
    public Either<ErrorWrapper, EditCommentContentResponse> process(EditCommentContentRequest input) {
        log.info("Start updating content comment");

        return Try.of(() -> {

                    EditCommentContentResponse output = EditCommentContentResponse.builder().build();
                    log.info("End updating content comment");
                    return output;
                })
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }



}
