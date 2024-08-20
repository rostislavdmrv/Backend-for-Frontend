package com.tinqinacademy.bff.core.processor.myhotel.hotel;

import com.tinqinacademy.bff.api.models.errors.ErrorWrapper;
import com.tinqinacademy.bff.api.models.output.VisitorReportResponse;
import com.tinqinacademy.bff.api.operations.hotel.retrivesreports.ReportOperation;
import com.tinqinacademy.bff.api.operations.hotel.retrivesreports.ReportRequest;
import com.tinqinacademy.bff.api.operations.hotel.retrivesreports.ReportResponse;
import com.tinqinacademy.bff.core.errorhandler.ErrorHandler;
import com.tinqinacademy.bff.core.processor.base.BaseOperationProcessor;
import com.tinqinacademy.myhotel.api.models.output.VisitorReportOutput;
import com.tinqinacademy.myhotel.api.operations.retrivesreports.ReportOutput;
import com.tinqinacademy.myhotel.restexport.HotelClient;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReportOperationProcessor extends BaseOperationProcessor<ReportRequest, ReportResponse> implements ReportOperation {

    private final HotelClient hotelClient;

    protected ReportOperationProcessor(ConversionService conversionService, Validator validator, ErrorHandler errorHandler, HotelClient hotelClient) {
        super(conversionService, validator, errorHandler);
        this.hotelClient = hotelClient;
    }

    @Override
    public Either<ErrorWrapper, ReportResponse> process(ReportRequest input) {
        return Try.of(() -> {
                    log.info("Start report with input: {}", input);
                    validateInput(input);

                    ReportOutput requestReportOutput = hotelClient.getReport(
                            input.getStartDate(),
                            input.getEndDate(),
                            input.getFirstName(),
                            input.getLastName(),
                            input.getPhoneNo(),
                            input.getIdCardNo(),
                            input.getIdCardValidity(),
                            input.getIdCardIssueAuthority(),
                            input.getIdCardIssueDate(),
                            input.getRoomNo()
                    );

                    ReportResponse output = conversionService.convert(requestReportOutput, ReportResponse.class);
                    log.info("End report with output: {}", output);
                    return output;
                })
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }
}
