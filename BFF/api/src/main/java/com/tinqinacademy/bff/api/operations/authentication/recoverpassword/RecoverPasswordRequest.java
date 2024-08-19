package com.tinqinacademy.bff.api.operations.authentication.recoverpassword;

import com.tinqinacademy.bff.api.base.OperationRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecoverPasswordRequest implements OperationRequest {

    @Email(message = "Invalid email format")
    @Schema(example = "andrey.petrov@gmail.com")
    private String email;
}
