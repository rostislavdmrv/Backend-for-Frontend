package com.tinqinacademy.bff.api.operations.hotel.isroomavailable;

import com.tinqinacademy.bff.api.base.OperationResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomResponse implements OperationResponse {

    private List<String> ids;


}
