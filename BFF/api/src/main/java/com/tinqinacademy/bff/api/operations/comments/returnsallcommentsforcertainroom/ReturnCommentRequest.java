package com.tinqinacademy.bff.api.operations.comments.returnsallcommentsforcertainroom;

import com.tinqinacademy.bff.api.base.OperationRequest;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnCommentRequest implements OperationRequest {
    private String roomId;
}
