package com.tinqinacademy.bff.api.operations.hotel.updatescertainroomsbyadmin;

import com.tinqinacademy.bff.api.base.OperationResponse;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UpdateRoomResponse implements OperationResponse {
    private String roomId;
}
