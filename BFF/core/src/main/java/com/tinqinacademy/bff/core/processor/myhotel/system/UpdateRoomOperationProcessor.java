package com.tinqinacademy.bff.core.processor.myhotel.system;

import com.tinqinacademy.bff.api.models.errors.ErrorWrapper;
import com.tinqinacademy.bff.api.operations.hotel.updatescertainroomsbyadmin.UpdateRoomOperation;
import com.tinqinacademy.bff.api.operations.hotel.updatescertainroomsbyadmin.UpdateRoomRequest;
import com.tinqinacademy.bff.api.operations.hotel.updatescertainroomsbyadmin.UpdateRoomResponse;
import com.tinqinacademy.bff.core.errorhandler.ErrorHandler;
import com.tinqinacademy.bff.core.processor.base.BaseOperationProcessor;
import com.tinqinacademy.myhotel.api.operations.updatescertainroomsbyadmin.UpdateRoomInput;
import com.tinqinacademy.myhotel.api.operations.updatescertainroomsbyadmin.UpdateRoomOutput;
import com.tinqinacademy.myhotel.restexport.HotelClient;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UpdateRoomOperationProcessor extends BaseOperationProcessor<UpdateRoomRequest, UpdateRoomResponse> implements UpdateRoomOperation {

    private final HotelClient hotelClient;

    protected UpdateRoomOperationProcessor(ConversionService conversionService, Validator validator, ErrorHandler errorHandler, HotelClient hotelClient) {
        super(conversionService, validator, errorHandler);
        this.hotelClient = hotelClient;
    }

    @Override
    public Either<ErrorWrapper, UpdateRoomResponse> process(UpdateRoomRequest input) {
        return Try.of(() -> {
                    log.info("Start updateRoom with input: {}", input);
                    validateInput(input);
                    UpdateRoomInput requestInput = conversionService.convert(input, UpdateRoomInput.class);
                    UpdateRoomOutput requestOutput = hotelClient.updateAlreadyCreatedRoomInSystem(input.getRoomId(), requestInput);

                    UpdateRoomResponse output = conversionService.convert(requestOutput, UpdateRoomResponse.class);
                    log.info("End updateRoom with output: {}", output);
                    return output;
                })
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }
}
