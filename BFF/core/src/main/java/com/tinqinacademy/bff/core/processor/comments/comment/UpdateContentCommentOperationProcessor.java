package com.tinqinacademy.bff.core.processor.comments.comment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.bff.api.models.errors.ErrorWrapper;
import com.tinqinacademy.bff.api.operations.comments.editcommentbyuser.EditCommentContentOperation;
import com.tinqinacademy.bff.api.operations.comments.editcommentbyuser.EditCommentContentRequest;
import com.tinqinacademy.bff.api.operations.comments.editcommentbyuser.EditCommentContentResponse;
import com.tinqinacademy.bff.core.errorhandler.ErrorHandler;
import com.tinqinacademy.bff.core.processor.base.BaseOperationProcessor;
import com.tinqinacademy.comments.api.operations.editcommentcontentbyuser.EditCommentContentInput;
import com.tinqinacademy.comments.api.operations.editcommentcontentbyuser.EditCommentContentOutput;
import com.tinqinacademy.comments.restexport.CommentsClient;
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
    private final CommentsClient commentsClient;


    protected UpdateContentCommentOperationProcessor(ConversionService conversionService, Validator validator, ErrorHandler errorHandler, ObjectMapper objectMapper, CommentsClient commentsClient) {
        super(conversionService, validator, errorHandler);
        this.objectMapper = objectMapper;
        this.commentsClient = commentsClient;
    }


    @Override
    public Either<ErrorWrapper, EditCommentContentResponse> process(EditCommentContentRequest input) {

        return Try.of(() -> {
                    log.info("Start contentUpdateComment with input: {}", input);
                    validateInput(input);
                    EditCommentContentInput requestInput = conversionService.convert(input, EditCommentContentInput.class);
                    EditCommentContentOutput requestOutput = commentsClient.updateContentComment(input.getCommentId(), requestInput);
                    EditCommentContentResponse output = conversionService.convert(requestOutput, EditCommentContentResponse.class);
                    log.info("End contentUpdateComment with output: {}", output);
                    return output;
                })
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }



}
