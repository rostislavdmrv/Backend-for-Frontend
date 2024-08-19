package com.tinqinacademy.bff.api.operations.authentication.validatejwt;

import com.tinqinacademy.bff.api.base.OperationResponse;
import lombok.*;
import org.springframework.security.core.userdetails.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ValidateJwtResponse implements OperationResponse {
    private User user;
}
