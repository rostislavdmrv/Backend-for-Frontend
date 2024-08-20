package com.tinqinacademy.bff.api.operations.comments.editcommentallbyadmin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.bff.api.base.OperationRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class EditCommentAllRequest implements OperationRequest {


    @NotBlank(message = "Comment ID cannot be blank")
    @JsonIgnore
    private String commentId;

    @UUID(message = "Room id must be in  UUID syntax")
    @NotBlank(message = "Room id not provided")
    private String roomId;


    @UUID(message = "User id must be in UUID syntax")
    @NotBlank(message = "User id cannot be blank")
    private String userId;

    @NotBlank(message = "Content name cannot be blank")
    @Size(min =2,max = 100, message = "content name cannot exceed 100 characters")
    private String content;
}
