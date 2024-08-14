package com.tinqinacademy.bff.core.mappers;

import com.tinqinacademy.bff.api.operations.comments.leavescommentsforcertainroom.LeaveCommentRequest;
import com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom.LeaveCommentInput;
import org.springframework.stereotype.Component;

@Component
public class LeaveCommentMapper {
    public LeaveCommentInput toLeaveCommentInput(LeaveCommentRequest request) {
        if (request == null) {
            return null;
        }

        return LeaveCommentInput.builder()
                .roomId(request.getRoomId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .content(request.getContent())
                .build();
    }

    public LeaveCommentRequest toLeaveCommentRequest(LeaveCommentInput input) {
        if (input == null) {
            return null;
        }

        return LeaveCommentRequest.builder()
                .roomId(input.getRoomId())
                .firstName(input.getFirstName())
                .lastName(input.getLastName())
                .content(input.getContent())
                .build();
    }
}
