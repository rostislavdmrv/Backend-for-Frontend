package com.tinqinacademy.bff.core.processor.comments.system;
import com.tinqinacademy.bff.api.models.errors.ErrorWrapper;
import com.tinqinacademy.bff.api.operations.comments.editcommentallbyadmin.EditCommentAllRequest;
import com.tinqinacademy.bff.api.operations.comments.editcommentallbyadmin.EditCommentAllResponse;
import com.tinqinacademy.bff.api.operations.comments.editcommentallbyadmin.EditCommentWholeOperation;
import com.tinqinacademy.bff.core.errorhandler.ErrorHandler;
import com.tinqinacademy.bff.core.processor.base.BaseOperationProcessor;
import com.tinqinacademy.comments.api.operations.editcommentallbyadmin.EditCommentAllInput;
import com.tinqinacademy.comments.api.operations.editcommentallbyadmin.EditCommentAllOutput;
import com.tinqinacademy.comments.restexport.CommentsClient;
import com.tinqinacademy.myhotel.restexport.HotelClient;
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

    private final CommentsClient commentsClient;
    private final HotelClient hotelClient;

    protected UpdateCommentProcessor(ConversionService conversionService, Validator validator, ErrorHandler errorHandler, CommentsClient commentsClient, HotelClient hotelClient) {
        super(conversionService, validator, errorHandler);
        this.commentsClient = commentsClient;
        this.hotelClient = hotelClient;
    }


    @Override
    public Either<ErrorWrapper, EditCommentAllResponse> process(EditCommentAllRequest input) {

        return Try.of(() -> {
                    log.info("Start adminUpdateComment with input: {}", input);
                    validateInput(input);
                    checkRoomIfProvided(input);
                    EditCommentAllInput requestInput = conversionService.convert(input, EditCommentAllInput.class);
                    EditCommentAllOutput requestOutput = commentsClient.updateComment(input.getCommentId(), requestInput);
                    EditCommentAllResponse output = conversionService.convert(requestOutput, EditCommentAllResponse.class);
                    log.info("End adminUpdateComment with output: {}", output);
                    return output;
                })
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }

    private void checkRoomIfProvided(EditCommentAllRequest input) {
        if (input.getRoomId() != null) {
            hotelClient.infoForRoom(input.getRoomId());
        }
    }



}
