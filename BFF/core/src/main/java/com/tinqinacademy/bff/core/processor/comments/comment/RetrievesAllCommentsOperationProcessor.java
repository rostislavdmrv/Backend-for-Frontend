package com.tinqinacademy.bff.core.processor.comments.comment;
import com.tinqinacademy.bff.api.models.errors.ErrorWrapper;
import com.tinqinacademy.bff.api.operations.comments.returnsallcommentsforcertainroom.ReturnCommentOperation;
import com.tinqinacademy.bff.api.operations.comments.returnsallcommentsforcertainroom.ReturnCommentRequest;
import com.tinqinacademy.bff.api.operations.comments.returnsallcommentsforcertainroom.ReturnCommentResponse;
import com.tinqinacademy.bff.core.errorhandler.ErrorHandler;
import com.tinqinacademy.bff.core.processor.base.BaseOperationProcessor;
import com.tinqinacademy.comments.api.operations.returnsallcommentsforcertainroom.ReturnCommentOutput;
import com.tinqinacademy.comments.restexport.CommentsClient;
import com.tinqinacademy.myhotel.restexport.HotelClient;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@Slf4j
public class RetrievesAllCommentsOperationProcessor extends BaseOperationProcessor<ReturnCommentRequest,ReturnCommentResponse> implements ReturnCommentOperation {

    private final CommentsClient commentsClient;
    private final HotelClient hotelClient;

    protected RetrievesAllCommentsOperationProcessor(ConversionService conversionService, Validator validator, ErrorHandler errorHandler, CommentsClient commentsClient, HotelClient hotelClient) {
        super(conversionService, validator, errorHandler);
        this.commentsClient = commentsClient;
        this.hotelClient = hotelClient;
    }


    @Override
    public Either<ErrorWrapper, ReturnCommentResponse> process(ReturnCommentRequest input) {
        log.info("Start retrieving all the comments for certain room");

        return Try.of(() -> {
                    validateInput(input);
                    hotelClient.infoForRoom(input.getRoomId());
                    ReturnCommentOutput requestOutput = commentsClient.retrievesAllComments(input.getRoomId());
                    ReturnCommentResponse output = conversionService.convert(requestOutput, ReturnCommentResponse.class);
                    log.info("End retrieving all the comments for certain room {}",output);
                    return output;

                })
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }

}
