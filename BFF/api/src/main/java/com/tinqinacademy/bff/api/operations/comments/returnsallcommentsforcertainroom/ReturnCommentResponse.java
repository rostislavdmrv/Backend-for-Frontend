package com.tinqinacademy.bff.api.operations.comments.returnsallcommentsforcertainroom;

import com.tinqinacademy.bff.api.base.OperationResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnCommentResponse implements OperationResponse {
    private List<CommentInfo> comments;
}
