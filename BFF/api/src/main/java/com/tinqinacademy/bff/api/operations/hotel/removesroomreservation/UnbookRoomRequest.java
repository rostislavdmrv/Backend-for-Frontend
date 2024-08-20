package com.tinqinacademy.bff.api.operations.hotel.removesroomreservation;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class UnbookRoomRequest implements OperationRequest {

    @JsonIgnore
    @UUID
    @NotBlank(message = "User ID cannot be blank")
    private String userId;

    @NotBlank(message = "Booking ID cannot be blank")
    @UUID
    private String bookingId;
}
