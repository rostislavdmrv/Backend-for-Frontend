package com.tinqinacademy.bff.core.converters.comments;

import com.tinqinacademy.bff.api.operations.comments.leavescommentsforcertainroom.LeaveCommentResponse;
import com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom.LeaveCommentOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LeaveCommentOutputToLeaveCommentResponseConverter implements Converter<LeaveCommentOutput, LeaveCommentResponse> {
    @Override
    public LeaveCommentResponse convert(LeaveCommentOutput source) {
        log.info("Start converting from LeaveCommentOutput to LeaveCommentResponse with input: {}", source);
        LeaveCommentResponse output = LeaveCommentResponse.builder()
                .id(source.getId())
                .build();
        log.info("End converting from LeaveCommentOutput to LeaveCommentResponse with output: {}", output);
        return output;
    }
}
