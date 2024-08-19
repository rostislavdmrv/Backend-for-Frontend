package com.tinqinacademy.bff.api.operations.authentication.login;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.bff.api.base.OperationResponse;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class LoginResponse implements OperationResponse {

    @JsonIgnore
    private String token;
}
