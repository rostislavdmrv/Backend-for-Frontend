package com.tinqinacademy.bff.rest.controllers.comments;

import com.tinqinacademy.comments.api.operations.editcommentallbyadmin.EditCommentAllInput;
import com.tinqinacademy.comments.restexport.CommentsClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Comments Client system - REST APIs")
public class SystemController {
    private final CommentsClient commentsClient;

    @PutMapping("/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable("commentId") String commentId, @RequestBody EditCommentAllInput input) {
        return new ResponseEntity<>(commentsClient.updateComment(commentId,input), HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable String commentId) {
        return new ResponseEntity<>(commentsClient.deleteComment(commentId), HttpStatus.OK);
    }


}
