package com.tinqinacademy.bff.core.processor.myhotel.hotel;

import com.tinqinacademy.bff.api.models.errors.ErrorWrapper;
import com.tinqinacademy.bff.api.operations.comments.leavescommentsforcertainroom.LeaveCommentResponse;
import com.tinqinacademy.bff.api.operations.hotel.booksroomspecified.BookRoomOperation;
import com.tinqinacademy.bff.api.operations.hotel.booksroomspecified.BookRoomRequest;
import com.tinqinacademy.bff.api.operations.hotel.booksroomspecified.BookRoomResponse;
import com.tinqinacademy.bff.core.errorhandler.ErrorHandler;
import com.tinqinacademy.bff.core.processor.base.BaseOperationProcessor;
import com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom.LeaveCommentInput;
import com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom.LeaveCommentOutput;
import com.tinqinacademy.myhotel.api.operations.booksroomspecified.BookRoomInput;
import com.tinqinacademy.myhotel.restexport.HotelClient;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookRoomOperationProcessor extends BaseOperationProcessor<BookRoomRequest, BookRoomResponse> implements BookRoomOperation {

    private final HotelClient hotelClient;

    protected BookRoomOperationProcessor(ConversionService conversionService, Validator validator, ErrorHandler errorHandler, HotelClient hotelClient) {
        super(conversionService, validator, errorHandler);
        this.hotelClient = hotelClient;
    }

    @Override
    public Either<ErrorWrapper, BookRoomResponse> process(BookRoomRequest input) {

        return Try.of(() -> {
                    log.info("Start bookRoom input: {}", input);
                    validateInput(input);
                    BookRoomInput request = conversionService.convert(input, BookRoomInput.class);
                    hotelClient.bookRoom(input.getRoomId(), request);
                    BookRoomResponse output = BookRoomResponse.builder().build();
                    log.info("End bookRoom output: {}", output);
                    return output;
                })
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }
}
