package com.tinqinacademy.bff.core.converters.hotel;

import com.tinqinacademy.bff.api.models.output.VisitorReportResponse;
import com.tinqinacademy.myhotel.api.models.output.VisitorReportOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class VisitorReportOutputToVisitorReportResponseConverter implements Converter<VisitorReportOutput, VisitorReportResponse> {


    @Override
    public VisitorReportResponse convert(VisitorReportOutput source) {
        log.info("Start converting from VisitorReportOutput to VisitorReportResponse with input: {}",source);
        VisitorReportResponse output = VisitorReportResponse.builder()
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .phoneNo(source.getPhoneNo())
                .idCardIssueAuthority(source.getIdCardIssueAuthority())
                .idCardIssueDate(source.getIdCardIssueDate())
                .idCardValidity(source.getIdCardValidity())
                .idCardNo(source.getIdCardNo())
                .build();
        log.info("End converting from VisitorReportOutput to VisitorReportResponse with output: {}", output);
        return output;
    }
}
