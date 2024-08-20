package com.tinqinacademy.bff.core.converters.comments;

import com.tinqinacademy.bff.api.operations.comments.returnsallcommentsforcertainroom.ReturnCommentResponse;
import com.tinqinacademy.comments.api.operations.returnsallcommentsforcertainroom.ReturnCommentOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReturnCommentOutputToReturnCommentResponseConverter implements Converter<ReturnCommentOutput, ReturnCommentResponse> {

    private final CommentInfToCommentInfoResponseConverter converterComments;

    @Override
    public ReturnCommentResponse convert(ReturnCommentOutput source) {
        log.info("Start converting from ReturnCommentOutput to ReturnCommentResponse with input: {}", source);

        ReturnCommentResponse output = ReturnCommentResponse.builder()
                .comments(source.getComments().stream()
                        .map(converterComments::convert)
                        .toList())
                .build();

        log.info("End converting from ReturnCommentOutput to ReturnCommentResponse with output: {}", output);
        return output;
    }
}
