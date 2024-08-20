package com.tinqinacademy.bff.api.operations.comments.returnsallcommentsforcertainroom;

import com.tinqinacademy.bff.api.base.OperationResponse;
import com.tinqinacademy.bff.api.models.output.CommentInfoResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnCommentResponse implements OperationResponse {
    private List<CommentInfoResponse> comments;
}
