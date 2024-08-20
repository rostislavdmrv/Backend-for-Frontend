package com.tinqinacademy.bff.core.processor.myhotel.hotel;

import com.tinqinacademy.bff.api.models.errors.ErrorWrapper;
import com.tinqinacademy.bff.api.operations.hotel.registersvisitors.RegisterVisitorOperation;
import com.tinqinacademy.bff.api.operations.hotel.registersvisitors.RegisterVisitorRequest;
import com.tinqinacademy.bff.api.operations.hotel.registersvisitors.RegisterVisitorResponse;
import com.tinqinacademy.bff.api.operations.hotel.removesroomreservation.UnbookRoomResponse;
import com.tinqinacademy.bff.core.errorhandler.ErrorHandler;
import com.tinqinacademy.bff.core.processor.base.BaseOperationProcessor;
import com.tinqinacademy.myhotel.api.operations.registersvisitors.RegisterVisitorInput;
import com.tinqinacademy.myhotel.restexport.HotelClient;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegisterVisitorOperationProcessor extends BaseOperationProcessor<RegisterVisitorRequest, RegisterVisitorResponse>  implements RegisterVisitorOperation {

    private final HotelClient hotelClient;

    protected RegisterVisitorOperationProcessor(ConversionService conversionService, Validator validator, ErrorHandler errorHandler, HotelClient hotelClient) {
        super(conversionService, validator, errorHandler);
        this.hotelClient = hotelClient;
    }

    @Override
    public Either<ErrorWrapper, RegisterVisitorResponse> process(RegisterVisitorRequest input) {
        return Try.of(() -> {
                    log.info("Start registerVisitor with input: {}", input);
                    validateInput(input);
                    RegisterVisitorInput requestInput = conversionService.convert(input, RegisterVisitorInput.class);
                    hotelClient.registerVisitor(requestInput);
                    RegisterVisitorResponse output = RegisterVisitorResponse.builder().build();
                    log.info("End registerVisitor with output: {}", output);
                    return output;
                })
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }
}
