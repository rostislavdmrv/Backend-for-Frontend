package com.tinqinacademy.bff.api.operations.hotel.returnsbasicinfoforroom;

import com.tinqinacademy.bff.api.base.OperationRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasicInfoRoomRequest implements OperationRequest {

    @NotBlank(message = "Room ID cannot be blank")
    private String roomId;
}
