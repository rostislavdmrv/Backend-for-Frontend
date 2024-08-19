package com.tinqinacademy.bff.api.operations.authentication.demote;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.tinqinacademy.bff.api.base.OperationRequest;
import com.tinqinacademy.bff.api.models.usertoken.UserToken;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DemoteRequest implements OperationRequest {

    @NotBlank(message = "User id  must not be blank!")
    @UUID
    private String userId;

    @JsonIgnore
    private UserToken userToken;
}
