package com.tinqinacademy.bff.rest.controllers.comments;

import com.tinqinacademy.comments.restexport.CommentsClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final CommentsClient commentsClient;

    @GetMapping("/comments/{roomId}")
    public ResponseEntity<?> retrievesAllComments(@PathVariable(name = "roomId") String roomId) {

        return new ResponseEntity<>(commentsClient.retrievesAllComments(roomId), HttpStatus.OK);
    }
}
