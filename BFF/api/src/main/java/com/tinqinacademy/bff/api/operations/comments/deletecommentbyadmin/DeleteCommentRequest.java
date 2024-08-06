package com.tinqinacademy.bff.api.operations.comments.deletecommentbyadmin;

import com.tinqinacademy.bff.api.base.OperationRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteCommentRequest implements OperationRequest {

    @NotBlank(message = "Commend ID cannot be blank")
    private String commendId;
}
