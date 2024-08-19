package com.tinqinacademy.bff.api.operations.authentication.confirmregistration;

import com.tinqinacademy.bff.api.base.OperationRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfirmRegistrationRequest implements OperationRequest {
    @NotBlank(message = "Confirmation code cannot be blank")
    private String confirmationCode;
}
