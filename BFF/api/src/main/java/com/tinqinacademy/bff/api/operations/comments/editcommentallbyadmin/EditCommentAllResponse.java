package com.tinqinacademy.bff.api.operations.comments.editcommentallbyadmin;

import com.tinqinacademy.bff.api.base.OperationResponse;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EditCommentAllResponse implements OperationResponse {
    private String id;
}
