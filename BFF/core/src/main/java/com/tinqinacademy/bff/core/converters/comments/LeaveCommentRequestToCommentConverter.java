package com.tinqinacademy.bff.core.converters.comments;

import com.tinqinacademy.bff.api.operations.comments.leavescommentsforcertainroom.LeaveCommentRequest;
import com.tinqinacademy.bff.persistence.models.entities.comments.Comment;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class LeaveCommentRequestToCommentConverter implements Converter<LeaveCommentRequest, Comment> {
    @Override
    public Comment convert(LeaveCommentRequest source) {
        Comment comment = Comment.builder()
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .content(source.getContent())
                .roomId(UUID.randomUUID())
                .publishDate(LocalDateTime.now())
                .build();
        return null;
    }
}
