package com.tinqinacademy.bff.api.operations.hotel.retrivesreports;

import com.tinqinacademy.bff.api.base.OperationResponse;
import com.tinqinacademy.myhotel.api.models.output.VisitorReportOutput;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportResponse implements OperationResponse {
    private List<VisitorReportOutput> reports;
}
