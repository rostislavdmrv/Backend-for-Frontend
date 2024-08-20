package com.tinqinacademy.bff.core.processor.myhotel.hotel;

import com.tinqinacademy.bff.api.models.errors.ErrorWrapper;
import com.tinqinacademy.bff.api.operations.hotel.isroomavailable.RoomResponse;
import com.tinqinacademy.bff.api.operations.hotel.returnsbasicinfoforroom.BasicInfoRoomOperation;
import com.tinqinacademy.bff.api.operations.hotel.returnsbasicinfoforroom.BasicInfoRoomRequest;
import com.tinqinacademy.bff.api.operations.hotel.returnsbasicinfoforroom.BasicInfoRoomResponse;
import com.tinqinacademy.bff.core.errorhandler.ErrorHandler;
import com.tinqinacademy.bff.core.processor.base.BaseOperationProcessor;
import com.tinqinacademy.myhotel.api.operations.isroomavailable.RoomOutput;
import com.tinqinacademy.myhotel.api.operations.returnsbasicinfoforroom.BasicInfoRoomOutput;
import com.tinqinacademy.myhotel.restexport.HotelClient;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BasicInfoRoomOperationProcessor extends BaseOperationProcessor<BasicInfoRoomRequest, BasicInfoRoomResponse> implements BasicInfoRoomOperation {

    private final HotelClient hotelClient;

    protected BasicInfoRoomOperationProcessor(ConversionService conversionService, Validator validator, ErrorHandler errorHandler, HotelClient hotelClient) {
        super(conversionService, validator, errorHandler);
        this.hotelClient = hotelClient;
    }

    @Override
    public Either<ErrorWrapper, BasicInfoRoomResponse> process(BasicInfoRoomRequest input) {
        return Try.of(() -> {
                    log.info("Start get basic info for room : {}", input);
                    validateInput(input);
                    BasicInfoRoomOutput requestOutput = hotelClient.infoForRoom(input.getRoomId());
                    BasicInfoRoomResponse output = conversionService.convert(requestOutput, BasicInfoRoomResponse.class);
                    log.info("End get basic info for room : {}", output);
                    return output;
                })
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }
}
