package com.tinqinacademy.bff.api.operations.hotel.registersvisitors;

import com.tinqinacademy.bff.api.base.OperationRequest;
import com.tinqinacademy.myhotel.api.models.input.VisitorInput;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterVisitorRequest implements OperationRequest {

    @Valid
    @NotEmpty
    private List<VisitorInput> visitorInputs;
}
