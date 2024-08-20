package com.tinqinacademy.bff.core.converters.comments;

import com.tinqinacademy.bff.api.operations.comments.editcommentbyuser.EditCommentContentRequest;
import com.tinqinacademy.comments.api.operations.editcommentcontentbyuser.EditCommentContentInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EditCommentContentIRequestToEditCommentContentInputConverter implements Converter<EditCommentContentRequest, EditCommentContentInput> {
    @Override
    public EditCommentContentInput convert(EditCommentContentRequest source) {
        log.info("Start converting from EditCommentContentRequest to EditCommentContentInput with input: {}", source);

        EditCommentContentInput output = EditCommentContentInput.builder()
                .content(source.getContent())
                .userId(source.getUserId())
                .build();

        log.info("End converting from EditCommentContentRequest to EditCommentContentInput with output: {}", output);
        return output;

    }
}
