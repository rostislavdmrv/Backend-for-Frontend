package com.tinqinacademy.bff.core.converters.hotel;

import com.tinqinacademy.bff.api.operations.hotel.returnsbasicinfoforroom.BasicInfoRoomResponse;
import com.tinqinacademy.myhotel.api.operations.returnsbasicinfoforroom.BasicInfoRoomOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BasicInfoRoomResponseToBasicInfoRoomOutputConverter implements Converter<BasicInfoRoomResponse, BasicInfoRoomOutput> {
    @Override
    public BasicInfoRoomOutput convert(BasicInfoRoomResponse source) {
        log.info("Start converting from BasicInfoRoomResponse to BasicInfoRoomOutput");

        BasicInfoRoomOutput output = BasicInfoRoomOutput.builder()
                .roomId(source.getRoomId())
                .price(source.getPrice())
                .floor(source.getFloor())
                .bedSize(source.getBedSize())
                .bathroomType(source.getBathroomType())
                .datesOccupied(source.getDatesOccupied())
                .build();

        log.info("End converting from BasicInfoRoomResponse to BasicInfoRoomOutput");
        return output;
    }
}
