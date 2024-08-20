package com.tinqinacademy.bff.core.converters.comments;

import com.tinqinacademy.bff.api.operations.comments.editcommentbyuser.EditCommentContentResponse;
import com.tinqinacademy.comments.api.operations.editcommentcontentbyuser.EditCommentContentOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EditCommentContentOutputToEditCommentContentResponseConverter implements Converter<EditCommentContentOutput,EditCommentContentResponse> {
    @Override
    public EditCommentContentResponse convert(EditCommentContentOutput source) {
        log.info("Start converting from EditCommentContentOutput to EditCommentContentResponse with input: {}", source);

        EditCommentContentResponse output = EditCommentContentResponse.builder()
                .id(source.getId())
                .build();

        log.info("End converting from EditCommentContentOutput to EditCommentContentResponse with output: {}", output);
        return output;
    }
}
