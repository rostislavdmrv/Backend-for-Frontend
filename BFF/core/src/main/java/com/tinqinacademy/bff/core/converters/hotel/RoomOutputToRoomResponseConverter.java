package com.tinqinacademy.bff.core.converters.hotel;

import com.tinqinacademy.bff.api.operations.hotel.isroomavailable.RoomResponse;
import com.tinqinacademy.myhotel.api.operations.isroomavailable.RoomOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RoomOutputToRoomResponseConverter implements Converter<RoomOutput, RoomResponse> {
    @Override
    public RoomResponse convert(RoomOutput source) {
        log.info("Start converting from RoomOutput to RoomResponse with input: {}", source);

        RoomResponse output = RoomResponse.builder()
                .ids(source.getIds())
                .build();

        log.info("End converting from RoomOutput to RoomResponse with output: {}", output);
        return output;
    }
}
