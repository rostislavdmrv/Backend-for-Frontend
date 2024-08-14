package com.tinqinacademy.bff.core.processor.comments.hotel;


import com.tinqinacademy.bff.api.models.errors.ErrorWrapper;
import com.tinqinacademy.bff.api.operations.comments.leavescommentsforcertainroom.LeaveCommentOperation;
import com.tinqinacademy.bff.api.operations.comments.leavescommentsforcertainroom.LeaveCommentRequest;
import com.tinqinacademy.bff.api.operations.comments.leavescommentsforcertainroom.LeaveCommentResponse;
import com.tinqinacademy.bff.core.errorhandler.ErrorHandler;
import com.tinqinacademy.bff.core.mappers.LeaveCommentMapper;
import com.tinqinacademy.bff.core.processor.base.BaseOperationProcessor;
import com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom.LeaveCommentInput;
import com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom.LeaveCommentOutput;
import com.tinqinacademy.comments.restexport.CommentsClient;
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
    private final LeaveCommentMapper leaveCommentMapper;


    protected LeaveCommentOperationProcessor(ConversionService conversionService, Validator validator, ErrorHandler errorHandler, CommentsClient commentsClient, LeaveCommentMapper leaveCommentMapper) {
        super(conversionService, validator, errorHandler);
        this.commentsClient = commentsClient;
        this.leaveCommentMapper = leaveCommentMapper;

    }


    @Override
    public Either<ErrorWrapper, LeaveCommentResponse> process(LeaveCommentRequest request) {

        return Try.of(() -> {
                    log.info("Start leaving comment");
                    LeaveCommentInput input = leaveCommentMapper.toLeaveCommentInput(request);
                    LeaveCommentOutput comment = commentsClient.leaveComment(request.getRoomId(), input);
                    LeaveCommentResponse output = LeaveCommentResponse.builder().id(comment.getId()).build();
                    log.info("End leaving comment");
                    return output;
                })
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }


}
