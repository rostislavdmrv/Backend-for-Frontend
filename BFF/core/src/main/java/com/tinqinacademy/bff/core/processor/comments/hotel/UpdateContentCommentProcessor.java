package com.tinqinacademy.bff.core.processor.comments.hotel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;

import com.tinqinacademy.bff.api.models.errors.ErrorWrapper;
import com.tinqinacademy.bff.api.operations.comments.editcommentbyuser.EditCommentContentOperation;
import com.tinqinacademy.bff.api.operations.comments.editcommentbyuser.EditCommentContentRequest;
import com.tinqinacademy.bff.api.operations.comments.editcommentbyuser.EditCommentContentResponse;
import com.tinqinacademy.bff.core.errorhandler.ErrorHandler;
import com.tinqinacademy.bff.core.processor.base.BaseOperationProcessor;
import com.tinqinacademy.bff.persistence.models.entities.comments.Comment;
import com.tinqinacademy.bff.persistence.repositories.comments.CommentRepository;
import com.tinqinacademy.comments.api.exceptions.ResourceNotFoundException;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class UpdateContentCommentProcessor extends BaseOperationProcessor<EditCommentContentRequest, EditCommentContentResponse> implements EditCommentContentOperation {

    private final CommentRepository commentRepository;
    private final ObjectMapper objectMapper;


    protected UpdateContentCommentProcessor(ConversionService conversionService, Validator validator, ErrorHandler errorHandler, CommentRepository commentRepository, ObjectMapper objectMapper) {
        super(conversionService, validator, errorHandler);
        this.commentRepository = commentRepository;
        this.objectMapper = objectMapper;
    }


    @Override
    public Either<ErrorWrapper, EditCommentContentResponse> process(EditCommentContentRequest input) {
        log.info("Start updating content comment");

        return Try.of(() -> {
                    Comment comment = retrieveComment(input.getContentId());
                    JsonNode patchedNode = applyPatchToComment(comment, input);
                    Comment patchedComment = objectMapper.treeToValue(patchedNode, Comment.class);
                    commentRepository.save(patchedComment);
                    EditCommentContentResponse output = buildEditCommentContentOutput(input.getContentId());
                    log.info("End updating content comment");
                    return output;
                })
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }
    private Comment retrieveComment(String contentId) {
        UUID commentId = UUID.fromString(contentId);
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId.toString()));
    }

    private JsonNode applyPatchToComment(Comment comment, EditCommentContentRequest input) throws JsonPatchException, IOException {
        JsonNode commentNode = objectMapper.valueToTree(comment);
        JsonNode inputNode = objectMapper.valueToTree(input);

        JsonMergePatch patch = JsonMergePatch.fromJson(inputNode);
        return patch.apply(commentNode);
    }

    private EditCommentContentResponse buildEditCommentContentOutput(String contentId) {
        return EditCommentContentResponse.builder()
                .id(contentId)
                .build();
    }


}
