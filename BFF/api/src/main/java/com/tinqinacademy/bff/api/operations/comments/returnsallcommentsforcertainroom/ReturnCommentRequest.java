package com.tinqinacademy.bff.api.operations.comments.returnsallcommentsforcertainroom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.bff.api.base.OperationRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnCommentRequest implements OperationRequest {
    @NotBlank
    @UUID
    @JsonIgnore
    private String roomId;
}
