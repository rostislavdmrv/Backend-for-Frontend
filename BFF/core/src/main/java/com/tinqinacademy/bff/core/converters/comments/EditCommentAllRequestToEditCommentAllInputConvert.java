package com.tinqinacademy.bff.core.converters.comments;

import com.tinqinacademy.bff.api.operations.comments.editcommentallbyadmin.EditCommentAllRequest;
import com.tinqinacademy.comments.api.operations.editcommentallbyadmin.EditCommentAllInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EditCommentAllRequestToEditCommentAllInputConvert implements Converter<EditCommentAllRequest, EditCommentAllInput> {
    @Override
    public EditCommentAllInput convert(EditCommentAllRequest source) {
        log.info("Start converting from EditCommentAllRequest to EditCommentAllInput with input: {}", source);

        EditCommentAllInput output = EditCommentAllInput.builder()
                .commentId(source.getCommentId())
                .content(source.getContent())
                .commentId(source.getCommentId())
                .roomId(source.getRoomId())
                .userId(source.getUserId())
                .build();

        log.info("End converting from EditCommentAllRequest to EditCommentAllInput with output: {}", output);
        return output;
    }
}
