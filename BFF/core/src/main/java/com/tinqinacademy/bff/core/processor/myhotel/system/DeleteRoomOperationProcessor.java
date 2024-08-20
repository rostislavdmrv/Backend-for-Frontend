package com.tinqinacademy.bff.core.processor.myhotel.system;

import com.tinqinacademy.bff.api.models.errors.ErrorWrapper;
import com.tinqinacademy.bff.api.operations.hotel.booksroomspecified.BookRoomResponse;
import com.tinqinacademy.bff.api.operations.hotel.deletesroomsbyadmin.DeleteRoomOperation;
import com.tinqinacademy.bff.api.operations.hotel.deletesroomsbyadmin.DeleteRoomRequest;
import com.tinqinacademy.bff.api.operations.hotel.deletesroomsbyadmin.DeleteRoomResponse;
import com.tinqinacademy.bff.core.errorhandler.ErrorHandler;
import com.tinqinacademy.bff.core.processor.base.BaseOperationProcessor;
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
public class DeleteRoomOperationProcessor extends BaseOperationProcessor<DeleteRoomRequest, DeleteRoomResponse> implements DeleteRoomOperation {

    private final HotelClient hotelClient;

    protected DeleteRoomOperationProcessor(ConversionService conversionService, Validator validator, ErrorHandler errorHandler, HotelClient hotelClient) {
        super(conversionService, validator, errorHandler);
        this.hotelClient = hotelClient;
    }

    @Override
    public Either<ErrorWrapper, DeleteRoomResponse> process(DeleteRoomRequest input) {
        return Try.of(() -> {
                    log.info("Start deleteRoom with input: {}", input);
                    validateInput(input);
                    hotelClient.deleteRooms(input.getRoomId());
                    DeleteRoomResponse output = DeleteRoomResponse.builder().build();
                    log.info("End deleteRoom with output: {}", output);
                    return output;
                })
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }
}
