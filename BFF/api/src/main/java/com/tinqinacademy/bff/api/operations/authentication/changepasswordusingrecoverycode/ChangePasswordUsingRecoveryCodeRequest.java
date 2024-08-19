package com.tinqinacademy.bff.api.operations.authentication.changepasswordusingrecoverycode;


import com.tinqinacademy.bff.api.base.OperationRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ChangePasswordUsingRecoveryCodeRequest implements OperationRequest {

    @NotBlank(message = "Recovery code cannot be blank")
    @Schema(example = "-> Check your email <-")
    private String recoveryCode;

    @NotBlank(message = "New password cannot be blank")
    @Size(min = 8, message = "New password must be at least 8 characters in length")
    @Schema(example = "sing1galena;")
    private String newPassword;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    @Schema(example = "atanas.hristov@outlook.com")
    private String email;
}
