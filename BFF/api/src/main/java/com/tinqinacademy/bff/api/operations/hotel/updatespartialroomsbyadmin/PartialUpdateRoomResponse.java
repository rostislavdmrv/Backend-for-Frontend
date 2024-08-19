package com.tinqinacademy.bff.api.operations.hotel.updatespartialroomsbyadmin;

import com.tinqinacademy.bff.api.base.OperationResponse;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartialUpdateRoomResponse implements OperationResponse {
    private String roomId;
}
