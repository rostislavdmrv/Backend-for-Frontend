package com.tinqinacademy.bff.core.converters.hotel;

import com.tinqinacademy.bff.api.operations.hotel.updatespartialroomsbyadmin.PartialUpdateRoomResponse;
import com.tinqinacademy.myhotel.api.operations.updatespartialroomsbyadmin.PartialUpdateRoomOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PartialUpdateRoomOutputToPartialUpdateRoomResponseConverter implements Converter<PartialUpdateRoomOutput, PartialUpdateRoomResponse> {
    @Override
    public PartialUpdateRoomResponse convert(PartialUpdateRoomOutput source) {
        log.info("Start converting from PartialUpdateRoomOutput to PartialUpdateRoomResponse with input: {}", source);

        PartialUpdateRoomResponse output = PartialUpdateRoomResponse.builder()
                .roomId(source.getRoomId())
                .build();

        log.info("End converting from PartialUpdateRoomOutput to PartialUpdateRoomResponse with output: {}", output);
        return output;
    }
}
