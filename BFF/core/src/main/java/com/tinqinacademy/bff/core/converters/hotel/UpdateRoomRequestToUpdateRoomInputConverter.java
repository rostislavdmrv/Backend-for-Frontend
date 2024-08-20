package com.tinqinacademy.bff.core.converters.hotel;

import com.tinqinacademy.bff.api.operations.hotel.updatescertainroomsbyadmin.UpdateRoomRequest;
import com.tinqinacademy.myhotel.api.operations.updatescertainroomsbyadmin.UpdateRoomInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UpdateRoomRequestToUpdateRoomInputConverter implements Converter<UpdateRoomRequest, UpdateRoomInput> {
    @Override
    public UpdateRoomInput convert(UpdateRoomRequest source) {
        log.info("Start converting from UpdateRoomRequest to UpdateRoomInput with input: {}", source);

        UpdateRoomInput output = UpdateRoomInput.builder()
                .bathroomType(source.getBathroomType())
                .beds(source.getBeds())
                .price(source.getPrice())
                .roomNo(source.getRoomNumber())
                .build();

        log.info("End converting from UpdateRoomRequest to UpdateRoomInput with output: {}", output);
        return output;
    }
}
