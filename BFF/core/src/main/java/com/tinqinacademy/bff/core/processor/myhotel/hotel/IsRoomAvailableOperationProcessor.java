package com.tinqinacademy.bff.core.processor.myhotel.hotel;

import com.tinqinacademy.bff.api.models.errors.ErrorWrapper;
import com.tinqinacademy.bff.api.operations.hotel.isroomavailable.IsRoomFreeOperation;
import com.tinqinacademy.bff.api.operations.hotel.isroomavailable.RoomRequest;
import com.tinqinacademy.bff.api.operations.hotel.isroomavailable.RoomResponse;
import com.tinqinacademy.bff.core.errorhandler.ErrorHandler;
import com.tinqinacademy.bff.core.processor.base.BaseOperationProcessor;
import com.tinqinacademy.myhotel.api.operations.isroomavailable.RoomOutput;
import com.tinqinacademy.myhotel.restexport.HotelClient;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class IsRoomAvailableOperationProcessor extends BaseOperationProcessor<RoomRequest, RoomResponse> implements IsRoomFreeOperation {

    private final HotelClient hotelClient;

    protected IsRoomAvailableOperationProcessor(ConversionService conversionService, Validator validator, ErrorHandler errorHandler, HotelClient hotelClient) {
        super(conversionService, validator, errorHandler);
        this.hotelClient = hotelClient;
    }

    @Override
    public Either<ErrorWrapper, RoomResponse> process(RoomRequest input) {
        return Try.of(() -> {
                    log.info("Start check room is available : {}", input);
                    validateInput(input);
                    RoomOutput requestOutput = hotelClient.isRoomAvailable(input.getStartDate(),
                            input.getEndDate(),
                            input.getBedCount(),
                            input.getBedSize(),
                            input.getBathroomType());

                    RoomResponse output = conversionService.convert(requestOutput, RoomResponse.class);
                    log.info("End check room is available : {}", output);
                    return output;
                })
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }
}
