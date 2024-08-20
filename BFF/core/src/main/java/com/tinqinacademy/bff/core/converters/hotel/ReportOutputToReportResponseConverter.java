package com.tinqinacademy.bff.core.converters.hotel;

import com.tinqinacademy.bff.api.operations.hotel.retrivesreports.ReportResponse;
import com.tinqinacademy.myhotel.api.operations.retrivesreports.ReportOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReportOutputToReportResponseConverter implements Converter<ReportOutput, ReportResponse> {

    private final VisitorReportOutputToVisitorReportResponseConverter visitorReportConverter;

    @Override
    public ReportResponse convert(ReportOutput source) {
        log.info("Start converting from ReportOutput to ReportResponse with input: {}", source);

        ReportResponse output = ReportResponse.builder()
                .reports(source.getReports().stream()
                        .map(visitorReportConverter::convert)
                        .toList())
                .build();

        log.info("End converting from ReportOutput to ReportResponse with output: {}", output);
        return output;
    }
}
