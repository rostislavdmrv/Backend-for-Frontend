package com.tinqinacademy.bff.core.converters.hotel;

import com.tinqinacademy.bff.api.operations.hotel.updatescertainroomsbyadmin.UpdateRoomResponse;
import com.tinqinacademy.myhotel.api.operations.updatescertainroomsbyadmin.UpdateRoomOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UpdateRoomOutputToUpdateRoomResponseConverter implements Converter<UpdateRoomOutput, UpdateRoomResponse> {
    @Override
    public UpdateRoomResponse convert(UpdateRoomOutput source) {
        log.info("Start converting from UpdateRoomOutput to UpdateRoomResponse with input: {}", source);

        UpdateRoomResponse output = UpdateRoomResponse.builder()
                .roomId(source.getRoomId())
                .build();

        log.info("End converting from UpdateRoomOutput to UpdateRoomResponse with output: {}", output);
        return output;
    }
}
