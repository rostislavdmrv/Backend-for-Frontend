package com.tinqinacademy.bff.api.operations.comments.deletecommentbyadmin;

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
public class DeleteCommentRequest implements OperationRequest {

    @UUID
    @NotBlank(message = "Commend ID cannot be blank")
    @JsonIgnore
    private String commentId;
}
