package com.tinqinacademy.bff.api.operations.comments.deletecommentbyadmin;

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
    private String commendId;
}
