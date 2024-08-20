package com.tinqinacademy.bff.core.converters.hotel;

import com.tinqinacademy.bff.api.operations.hotel.updatespartialroomsbyadmin.PartialUpdateRoomRequest;
import com.tinqinacademy.myhotel.api.operations.updatespartialroomsbyadmin.PartialUpdateRoomInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PartialUpdateRoomRequestToPartialUpdateRoomInputConverter implements Converter<PartialUpdateRoomRequest, PartialUpdateRoomInput> {
    @Override
    public PartialUpdateRoomInput convert(PartialUpdateRoomRequest source) {
        log.info("Start converting from PartialUpdateRoomRequest to PartialUpdateRoomInput with input: {}", source);

        PartialUpdateRoomInput output = PartialUpdateRoomInput.builder()
                .bathroomType(source.getBathroomType())
                .roomNumber(source.getRoomNumber())
                .beds(source.getBeds())
                .price(source.getPrice())
                .build();

        log.info("End converting from PartialUpdateRoomRequest to PartialUpdateRoomInput with input: {}", source);
        return output;
    }
}
