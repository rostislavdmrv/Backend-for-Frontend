package com.tinqinacademy.bff.rest.controllers.comments;

import com.tinqinacademy.bff.api.models.usertoken.CustomUser;
import com.tinqinacademy.bff.api.operations.comments.deletecommentbyadmin.DeleteCommentOperation;
import com.tinqinacademy.bff.api.operations.comments.deletecommentbyadmin.DeleteCommentRequest;
import com.tinqinacademy.bff.api.operations.comments.editcommentallbyadmin.EditCommentAllRequest;
import com.tinqinacademy.bff.api.operations.comments.editcommentallbyadmin.EditCommentWholeOperation;
import com.tinqinacademy.bff.api.operations.comments.editcommentbyuser.EditCommentContentOperation;
import com.tinqinacademy.bff.api.operations.comments.editcommentbyuser.EditCommentContentRequest;
import com.tinqinacademy.bff.api.operations.comments.leavescommentsforcertainroom.LeaveCommentOperation;
import com.tinqinacademy.bff.api.operations.comments.leavescommentsforcertainroom.LeaveCommentRequest;
import com.tinqinacademy.bff.api.operations.comments.returnsallcommentsforcertainroom.ReturnCommentOperation;
import com.tinqinacademy.bff.api.operations.comments.returnsallcommentsforcertainroom.ReturnCommentRequest;
import com.tinqinacademy.bff.api.restapiroutes.RestApiRoutes;
import com.tinqinacademy.bff.rest.controllers.base.BaseController;
import com.tinqinacademy.comments.api.operations.editcommentallbyadmin.EditCommentAllInput;
import com.tinqinacademy.comments.api.operations.editcommentcontentbyuser.EditCommentContentInput;
import com.tinqinacademy.comments.restexport.CommentsClient;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Comments Client - REST APIs")
@SecurityRequirement(name = "bearerAuth")
public class CommentsController extends BaseController {

    private final LeaveCommentOperation leaveCommentOperation;
    private final ReturnCommentOperation returnCommentOperation;
    private final EditCommentContentOperation editCommentContentOperation;
    private final EditCommentWholeOperation editCommentWholeOperation;
    private final DeleteCommentOperation deleteCommentOperation;


    @GetMapping(RestApiRoutes.RETRIEVE_ALL_COMMENTS)
    public ResponseEntity<?> retrievesAllComments(@PathVariable(name = "roomId") String roomId) {

        ReturnCommentRequest input = ReturnCommentRequest.builder()
                .roomId(roomId)
                .build();

        return handleWithStatus(returnCommentOperation.process(input),HttpStatus.OK);
    }

    @PostMapping(RestApiRoutes.LEAVE_COMMENT)
    @PreAuthorize("hasRole('USER')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> leaveComment(@PathVariable String roomId,@RequestBody LeaveCommentRequest input){

        CustomUser user = getUserFromContext();
        LeaveCommentRequest updated = input.toBuilder()
                .roomId(roomId)
                .userId(user.getUserId().toString())
                .build();
        return handleWithStatus(leaveCommentOperation.process(updated), HttpStatus.OK);
    }

    @PatchMapping(RestApiRoutes.UPDATE_COMMENT_CONTENT)
    @PreAuthorize("hasRole('USER')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> updateContentComment(@PathVariable("commentId") String commentId,@RequestBody EditCommentContentRequest input) {

        CustomUser user = getUserFromContext();
        EditCommentContentRequest updated = input.toBuilder()
                .commentId(commentId)
                .userId(user.getUserId().toString())
                .build();

        return handleWithStatus(editCommentContentOperation.process(updated), HttpStatus.OK);

    }


    @PutMapping(RestApiRoutes.UPDATE_COMMENT)
    public ResponseEntity<?> updateComment(@PathVariable("commentId") String commentId, @RequestBody EditCommentAllRequest input) {

        CustomUser user = getUserFromContext();
        EditCommentAllRequest updated = input.toBuilder()
                .commentId(commentId)
                .userId(user.getUserId().toString())
                .build();

        return handleWithStatus(editCommentWholeOperation.process(updated),HttpStatus.OK);
    }

    @DeleteMapping(RestApiRoutes.DELETE_COMMENT)
    public ResponseEntity<?> deleteComment(@PathVariable String commentId) {

        DeleteCommentRequest input = DeleteCommentRequest.builder()
                .commentId(commentId)
                .build();
        return handleWithStatus(deleteCommentOperation.process(input),HttpStatus.OK);

    }
}
