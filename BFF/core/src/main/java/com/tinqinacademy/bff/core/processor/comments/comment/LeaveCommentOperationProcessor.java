package com.tinqinacademy.bff.core.processor.comments.comment;

import com.tinqinacademy.bff.api.models.errors.ErrorWrapper;
import com.tinqinacademy.bff.api.operations.comments.leavescommentsforcertainroom.LeaveCommentOperation;
import com.tinqinacademy.bff.api.operations.comments.leavescommentsforcertainroom.LeaveCommentRequest;
import com.tinqinacademy.bff.api.operations.comments.leavescommentsforcertainroom.LeaveCommentResponse;
import com.tinqinacademy.bff.core.errorhandler.ErrorHandler;
import com.tinqinacademy.bff.core.processor.base.BaseOperationProcessor;

import com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom.LeaveCommentInput;
import com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom.LeaveCommentOutput;
import com.tinqinacademy.comments.restexport.CommentsClient;
import com.tinqinacademy.myhotel.restexport.HotelClient;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LeaveCommentOperationProcessor extends BaseOperationProcessor<LeaveCommentRequest, LeaveCommentResponse> implements LeaveCommentOperation {

    private final CommentsClient commentsClient;
    private final HotelClient hotelClient;



    protected LeaveCommentOperationProcessor(ConversionService conversionService, Validator validator, ErrorHandler errorHandler, CommentsClient commentsClient, HotelClient hotelClient) {
        super(conversionService, validator, errorHandler);
        this.commentsClient = commentsClient;
        this.hotelClient = hotelClient;
    }


    @Override
    public Either<ErrorWrapper, LeaveCommentResponse> process(LeaveCommentRequest input) {

        return Try.of(() -> {
                    log.info("Start publishComment with input: {}", input);
                    validateInput(input);
                    hotelClient.infoForRoom(input.getRoomId());
                    LeaveCommentInput requestInput = conversionService.convert(input, LeaveCommentInput.class);
                    LeaveCommentOutput requestOutput = commentsClient.leaveComment(input.getRoomId(), requestInput);
                    LeaveCommentResponse output = conversionService.convert(requestOutput, LeaveCommentResponse.class);
                    log.info("End publishComment with output: {}", output);
                    return output;
                })
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }


}
