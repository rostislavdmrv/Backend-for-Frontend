package com.tinqinacademy.bff.core.converters.comments;

import com.tinqinacademy.bff.api.models.output.CommentInfoResponse;
import com.tinqinacademy.comments.api.models.output.CommentInf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommentInfToCommentInfoResponseConverter implements Converter<CommentInf, CommentInfoResponse> {
    @Override
    public CommentInfoResponse convert(CommentInf source) {
        log.info("Start converting from CommentInf to CommentInfoResponse with input: {}", source);

        CommentInfoResponse output = CommentInfoResponse.builder()
                .id(source.getId())
                //.userId(source.)
                .content(source.getContent())
                .publishDate(source.getPublishDate())
                .lastEditedBy(source.getLastEditedBy())
                .lastEditedDate(source.getLastEditedDate())
                .build();

        log.info("End converting from CommentInf to CommentInfoResponse with output: {}", output);
        return output;
    }
}
