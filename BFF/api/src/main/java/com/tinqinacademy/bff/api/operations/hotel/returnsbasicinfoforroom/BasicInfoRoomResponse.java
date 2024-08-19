package com.tinqinacademy.bff.api.operations.hotel.returnsbasicinfoforroom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.bff.api.base.OperationResponse;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BasicInfoRoomResponse implements OperationResponse {

    @JsonIgnore
    private String roomId;
    private BigDecimal price;
    private Integer floor;
    private List<String> bedSize;
    private String bathroomType;
    private Integer bedCount;
    public List<LocalDate> datesOccupied;
}
