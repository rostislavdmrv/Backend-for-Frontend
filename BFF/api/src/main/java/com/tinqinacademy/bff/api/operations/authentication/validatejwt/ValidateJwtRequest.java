package com.tinqinacademy.bff.api.operations.authentication.validatejwt;

import com.tinqinacademy.bff.api.base.OperationRequest;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ValidateJwtRequest implements OperationRequest {

    private String authorizationHeader;
}
