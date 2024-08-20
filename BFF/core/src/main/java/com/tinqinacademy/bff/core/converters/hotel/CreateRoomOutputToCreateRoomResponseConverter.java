package com.tinqinacademy.bff.core.converters.hotel;

import com.tinqinacademy.bff.api.operations.hotel.createsnewroomsbyadmin.CreateRoomResponse;
import com.tinqinacademy.myhotel.api.operations.createsnewroomsbyadmin.CreateRoomOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreateRoomOutputToCreateRoomResponseConverter implements Converter<CreateRoomOutput, CreateRoomResponse> {
    @Override
    public CreateRoomResponse convert(CreateRoomOutput source) {
        log.info("Start converting from CreateRoomOutput to CreateRoomResponse with input: {}", source);

        CreateRoomResponse output = CreateRoomResponse.builder()
                .roomId(source.getRoomId())
                .build();

        log.info("End converting from CreateRoomOutput to CreateRoomResponse with output: {}", output);
        return output;
    }
}
