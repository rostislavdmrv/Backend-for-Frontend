package com.tinqinacademy.bff.core.processor.myhotel.hotel;

import com.tinqinacademy.bff.api.models.errors.ErrorWrapper;
import com.tinqinacademy.bff.api.operations.hotel.removesroomreservation.UnbookRoomOperation;
import com.tinqinacademy.bff.api.operations.hotel.removesroomreservation.UnbookRoomRequest;
import com.tinqinacademy.bff.api.operations.hotel.removesroomreservation.UnbookRoomResponse;
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
public class UnbookRoomOperationProcessor extends BaseOperationProcessor<UnbookRoomRequest, UnbookRoomResponse> implements UnbookRoomOperation {

    private final HotelClient hotelClient;
    protected UnbookRoomOperationProcessor(ConversionService conversionService, Validator validator, ErrorHandler errorHandler, HotelClient hotelClient) {
        super(conversionService, validator, errorHandler);
        this.hotelClient = hotelClient;
    }

    @Override
    public Either<ErrorWrapper, UnbookRoomResponse> process(UnbookRoomRequest input) {
        return Try.of(() -> {
                    log.info("Start unbookRoom with input: {}", input);
                    validateInput(input);
                    hotelClient.removeBookedRoom(input.getBookingId());

                    UnbookRoomResponse output = UnbookRoomResponse.builder().build();
                    log.info("End unbookRoom with output: {}", output);
                    return output;
                })
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }
}
