package com.tinqinacademy.bff.api.operations.hotel.deletesroomsbyadmin;

import com.tinqinacademy.bff.api.base.OperationRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteRoomRequest implements OperationRequest {

    @UUID
    @NotBlank(message = "Room ID cannot be blank")
    private String roomId;
}
