package com.tinqinacademy.bff.core.processor.myhotel.system;

import com.tinqinacademy.bff.api.models.errors.ErrorWrapper;
import com.tinqinacademy.bff.api.operations.hotel.deletesroomsbyadmin.DeleteRoomResponse;
import com.tinqinacademy.bff.api.operations.hotel.updatespartialroomsbyadmin.PartialUpdateRoomOperation;
import com.tinqinacademy.bff.api.operations.hotel.updatespartialroomsbyadmin.PartialUpdateRoomRequest;
import com.tinqinacademy.bff.api.operations.hotel.updatespartialroomsbyadmin.PartialUpdateRoomResponse;
import com.tinqinacademy.bff.core.errorhandler.ErrorHandler;
import com.tinqinacademy.bff.core.processor.base.BaseOperationProcessor;
import com.tinqinacademy.myhotel.api.operations.updatespartialroomsbyadmin.PartialUpdateRoomInput;
import com.tinqinacademy.myhotel.api.operations.updatespartialroomsbyadmin.PartialUpdateRoomOutput;
import com.tinqinacademy.myhotel.restexport.HotelClient;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PartialUpdateOperationProcessor extends BaseOperationProcessor<PartialUpdateRoomRequest, PartialUpdateRoomResponse> implements PartialUpdateRoomOperation {

    private final HotelClient hotelClient;

    protected PartialUpdateOperationProcessor(ConversionService conversionService, Validator validator, ErrorHandler errorHandler, HotelClient hotelClient) {
        super(conversionService, validator, errorHandler);
        this.hotelClient = hotelClient;
    }

    @Override
    public Either<ErrorWrapper, PartialUpdateRoomResponse> process(PartialUpdateRoomRequest input) {
        return Try.of(() -> {
                    log.info("Start partialUpdateRoom with input: {}", input);
                    PartialUpdateRoomInput requestInput = conversionService.convert(input, PartialUpdateRoomInput.class);
                    PartialUpdateRoomOutput requestOutput = hotelClient.updateRoomPartially(input.getRoomId(), requestInput);

                    PartialUpdateRoomResponse output = conversionService.convert(requestOutput, PartialUpdateRoomResponse.class);
                    log.info("End partialUpdate with output: {}", output);
                    return output;
                })
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }
}
