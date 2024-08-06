package com.tinqinacademy.bff.api.operations.comments.editcommentbyuser;

import com.tinqinacademy.bff.api.base.OperationResponse;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EditCommentContentResponse implements OperationResponse {
    private String id;
}
