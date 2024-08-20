package com.tinqinacademy.bff.core.converters.comments;

import com.tinqinacademy.bff.api.operations.comments.editcommentallbyadmin.EditCommentAllResponse;
import com.tinqinacademy.comments.api.operations.editcommentallbyadmin.EditCommentAllOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EditCommentAllOutputToEditCommentAllResponseConvert implements Converter<EditCommentAllOutput, EditCommentAllResponse> {
    @Override
    public EditCommentAllResponse convert(EditCommentAllOutput source) {
        log.info("Start converting from EditCommentAllOutput to EditCommentAllResponse with input: {}", source);

        EditCommentAllResponse output = EditCommentAllResponse.builder()
                .id(source.getId())
                .build();

        log.info("End converting from EditCommentAllOutput to EditCommentAllResponse with output: {}", output);
        return output;
    }
}
