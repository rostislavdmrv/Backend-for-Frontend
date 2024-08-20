package com.tinqinacademy.bff.core.processor.myhotel.system;

import com.tinqinacademy.bff.api.models.errors.ErrorWrapper;
import com.tinqinacademy.bff.api.operations.hotel.booksroomspecified.BookRoomResponse;
import com.tinqinacademy.bff.api.operations.hotel.createsnewroomsbyadmin.CreateRoomOperation;
import com.tinqinacademy.bff.api.operations.hotel.createsnewroomsbyadmin.CreateRoomRequest;
import com.tinqinacademy.bff.api.operations.hotel.createsnewroomsbyadmin.CreateRoomResponse;
import com.tinqinacademy.bff.core.errorhandler.ErrorHandler;
import com.tinqinacademy.bff.core.processor.base.BaseOperationProcessor;
import com.tinqinacademy.myhotel.api.operations.booksroomspecified.BookRoomInput;
import com.tinqinacademy.myhotel.api.operations.createsnewroomsbyadmin.CreateRoomInput;
import com.tinqinacademy.myhotel.api.operations.createsnewroomsbyadmin.CreateRoomOutput;
import com.tinqinacademy.myhotel.restexport.HotelClient;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreateRoomOperationProcessor extends BaseOperationProcessor<CreateRoomRequest, CreateRoomResponse> implements CreateRoomOperation {

    private  final HotelClient hotelClient;

    protected CreateRoomOperationProcessor(ConversionService conversionService, Validator validator, ErrorHandler errorHandler, HotelClient hotelClient) {
        super(conversionService, validator, errorHandler);
        this.hotelClient = hotelClient;
    }

    @Override
    public Either<ErrorWrapper, CreateRoomResponse> process(CreateRoomRequest input) {
        return Try.of(() -> {
                    log.info("Start createRoom with input: {}", input);
                    validateInput(input);
                    CreateRoomInput requestInput = conversionService.convert(input, CreateRoomInput.class);
                    CreateRoomOutput requestOutput = hotelClient.createRoom(requestInput);

                    CreateRoomResponse output = conversionService.convert(requestOutput, CreateRoomResponse.class);
                    log.info("End createRoom with output: {}", output);
                    return output;
                })
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }
}
