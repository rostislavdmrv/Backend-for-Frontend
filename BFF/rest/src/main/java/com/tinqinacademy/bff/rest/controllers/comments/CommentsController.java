package com.tinqinacademy.bff.rest.controllers.comments;

import com.tinqinacademy.bff.api.operations.comments.deletecommentbyadmin.DeleteCommentOperation;
import com.tinqinacademy.bff.api.operations.comments.editcommentallbyadmin.EditCommentWholeOperation;
import com.tinqinacademy.bff.api.operations.comments.editcommentbyuser.EditCommentContentOperation;
import com.tinqinacademy.bff.api.operations.comments.leavescommentsforcertainroom.LeaveCommentOperation;
import com.tinqinacademy.bff.api.operations.comments.leavescommentsforcertainroom.LeaveCommentRequest;
import com.tinqinacademy.bff.api.operations.comments.returnsallcommentsforcertainroom.ReturnCommentOperation;
import com.tinqinacademy.bff.api.operations.comments.returnsallcommentsforcertainroom.ReturnCommentRequest;
import com.tinqinacademy.bff.api.restapiroutes.RestApiRoutes;
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
    private final ReturnCommentOperation returnCommentOperation;
    private final EditCommentContentOperation editCommentContentOperation;
    private final EditCommentWholeOperation editCommentWholeOperation;
    private final DeleteCommentOperation deleteCommentOperation;

    private final CommentsClient commentsClient;

    @GetMapping(RestApiRoutes.RETRIEVE_ALL_COMMENTS)
    public ResponseEntity<?> retrievesAllComments(@PathVariable(name = "roomId") String roomId) {

        ReturnCommentRequest input = ReturnCommentRequest.builder()
                .roomId(roomId)
                .build();

        return handleWithStatus(returnCommentOperation.process(input),HttpStatus.OK);
    }

    @PostMapping(RestApiRoutes.LEAVE_COMMENT)
    public ResponseEntity<?> leaveComment(@PathVariable String roomId,@RequestBody LeaveCommentRequest input){

        LeaveCommentRequest updated = input.toBuilder().roomId(roomId).build();
        return handleWithStatus(leaveCommentOperation.process(updated), HttpStatus.OK);
    }

    @PatchMapping(RestApiRoutes.UPDATE_COMMENT_CONTENT)
    public ResponseEntity<?> updateContentComment(@PathVariable("commentId") String commentId,@RequestBody EditCommentContentInput input) {
        return new ResponseEntity<>(commentsClient.updateContentComment(commentId, input), HttpStatus.OK);

    }



    @PutMapping(RestApiRoutes.UPDATE_COMMENT)
    public ResponseEntity<?> updateComment(@PathVariable("commentId") String commentId, @RequestBody EditCommentAllInput input) {
        return new ResponseEntity<>(commentsClient.updateComment(commentId,input), HttpStatus.OK);
    }

    @DeleteMapping(RestApiRoutes.DELETE_COMMENT)
    public ResponseEntity<?> deleteComment(@PathVariable String commentId) {
        return new ResponseEntity<>(commentsClient.deleteComment(commentId), HttpStatus.OK);
    }
}
