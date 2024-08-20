package com.tinqinacademy.bff.core.converters.hotel;

import com.tinqinacademy.bff.api.operations.hotel.createsnewroomsbyadmin.CreateRoomRequest;
import com.tinqinacademy.myhotel.api.operations.createsnewroomsbyadmin.CreateRoomInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreateRoomRequestToCreateRoomInputConverter implements Converter<CreateRoomRequest, CreateRoomInput> {
    @Override
    public CreateRoomInput convert(CreateRoomRequest source) {
        log.info("Start converting from CreateRoomRequest to CreateRoomInput with input: {}", source);

        CreateRoomInput output = CreateRoomInput.builder()
                .beds(source.getBeds())
                .bathroomType(source.getBathroomType())
                .floor(source.getFloor())
                .roomNo(source.getRoomNumber())
                .price(source.getPrice())
                .build();

        log.info("End converting from CreateRoomRequest to CreateRoomInput with output: {}", output);
        return output;
    }
}
