package com.tinqinacademy.bff.api.operations.authentication.register;

import com.tinqinacademy.bff.api.base.OperationResponse;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterResponse implements OperationResponse {
    String id;
}
