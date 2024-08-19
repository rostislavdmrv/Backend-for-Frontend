package com.tinqinacademy.bff.api.operations.hotel.removesroomreservation;

import com.tinqinacademy.bff.api.base.OperationRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnbookRoomRequest implements OperationRequest {
    @NotBlank(message = "Booking ID cannot be blank")
    private String bookingId;
}
