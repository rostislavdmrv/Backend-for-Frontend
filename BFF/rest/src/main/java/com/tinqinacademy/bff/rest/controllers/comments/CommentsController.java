package com.tinqinacademy.bff.rest.controllers.comments;

import com.tinqinacademy.bff.api.operations.comments.leavescommentsforcertainroom.LeaveCommentOperation;
import com.tinqinacademy.bff.api.operations.comments.leavescommentsforcertainroom.LeaveCommentRequest;
import com.tinqinacademy.bff.rest.controllers.base.BaseController;
import com.tinqinacademy.comments.api.operations.editcommentallbyadmin.EditCommentAllInput;
import com.tinqinacademy.comments.api.operations.editcommentcontentbyuser.EditCommentContentInput;
import com.tinqinacademy.comments.restexport.CommentsClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Comments Client - REST APIs")
public class CommentsController extends BaseController {

    private final LeaveCommentOperation leaveCommentOperation;
    private final CommentsClient commentsClient;

    @GetMapping("/comments/{roomId}")
    public ResponseEntity<?> retrievesAllComments(@PathVariable(name = "roomId") String roomId) {

        return new ResponseEntity<>(commentsClient.retrievesAllComments(roomId), HttpStatus.OK);
    }

    @PostMapping("/comments/{roomId}")
    public ResponseEntity<?> leaveComment(@PathVariable String roomId,@RequestBody LeaveCommentRequest input){
        LeaveCommentRequest updated = input.toBuilder().roomId(roomId).build();
        return handleWithStatus(leaveCommentOperation.process(updated), HttpStatus.OK);
    }

    @PatchMapping("/{commentId}/comment")
    public ResponseEntity<?> updateContentComment(@PathVariable("commentId") String commentId,@RequestBody EditCommentContentInput input) {
        return new ResponseEntity<>(commentsClient.updateContentComment(commentId, input), HttpStatus.OK);
    }



    @PutMapping("/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable("commentId") String commentId, @RequestBody EditCommentAllInput input) {
        return new ResponseEntity<>(commentsClient.updateComment(commentId,input), HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable String commentId) {
        return new ResponseEntity<>(commentsClient.deleteComment(commentId), HttpStatus.OK);
    }
}
