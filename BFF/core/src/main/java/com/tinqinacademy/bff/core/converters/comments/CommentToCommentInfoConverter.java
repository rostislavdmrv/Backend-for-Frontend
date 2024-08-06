package com.tinqinacademy.bff.core.converters.comments;

import com.tinqinacademy.bff.api.operations.comments.returnsallcommentsforcertainroom.CommentInfo;
import com.tinqinacademy.bff.persistence.models.entities.comments.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommentToCommentInfoConverter implements Converter<Comment, CommentInfo> {
    @Override
    public CommentInfo convert(Comment source) {
        log.info("Start convert comment !");
        CommentInfo commentInfo = CommentInfo.builder()
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .content(source.getContent())
                .lastEditedDate(source.getLastEditedDate().toLocalDate())
                .publishDate(source.getPublishDate().toLocalDate())
                .lastEditedBy(source.getLastEditedBy().toString()).build();
        log.info("End convert comment !");
        return commentInfo;
    }
}
