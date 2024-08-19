package com.tinqinacademy.bff.api.operations.authentication.changepassword;

import com.tinqinacademy.bff.api.base.OperationRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangePasswordRequest implements OperationRequest {
    @NotBlank(message = "Old password must not be blank")
    @Schema(example = "rosti1111;")
    private String oldPassword;
    @NotBlank(message = "New password must not be blank")
    @Size(min = 8, message = "New password must be at least 8 characters in length")
    @Schema(example = "newRosti11;")
    private String newPassword;
    @Email(message = "Invalid email format")
    @Schema(example = "atanas.atanasov2005@gmail.com")
    private String email;
}
