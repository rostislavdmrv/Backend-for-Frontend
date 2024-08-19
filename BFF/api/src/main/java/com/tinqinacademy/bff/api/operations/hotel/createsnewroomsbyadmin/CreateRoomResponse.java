package com.tinqinacademy.bff.api.operations.hotel.createsnewroomsbyadmin;

import com.tinqinacademy.bff.api.base.OperationResponse;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateRoomResponse implements OperationResponse {
    private String roomId;
}
