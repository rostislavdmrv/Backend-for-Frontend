package com.tinqinacademy.bff.core.converters.comments;

import com.tinqinacademy.bff.api.operations.comments.leavescommentsforcertainroom.LeaveCommentRequest;
import com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom.LeaveCommentInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LeaveCommentRequestToLeaveCommentInputConverter implements Converter<LeaveCommentRequest, LeaveCommentInput> {
    @Override
    public LeaveCommentInput convert(LeaveCommentRequest source) {
        log.info("Start converting from LeaveCommentRequest to LeaveCommentInput with input: {}", source);

        LeaveCommentInput output = LeaveCommentInput.builder()
                .content(source.getContent())
                //.userId(source.getUserId())
                .build();

        log.info("End converting from LeaveCommentRequest to LeaveCommentInput with output: {}", output);
        return output;
    }
}
