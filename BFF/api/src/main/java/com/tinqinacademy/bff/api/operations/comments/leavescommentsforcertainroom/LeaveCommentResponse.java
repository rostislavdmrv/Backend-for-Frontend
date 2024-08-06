package com.tinqinacademy.bff.api.operations.comments.leavescommentsforcertainroom;

import com.tinqinacademy.bff.api.base.OperationResponse;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveCommentResponse implements OperationResponse {
    private String id;
}
