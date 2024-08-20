package com.tinqinacademy.bff.api.operations.hotel.retrivesreports;

import com.tinqinacademy.bff.api.base.OperationResponse;
import com.tinqinacademy.bff.api.models.output.VisitorReportResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportResponse implements OperationResponse {
    private List<VisitorReportResponse> reports;
}
