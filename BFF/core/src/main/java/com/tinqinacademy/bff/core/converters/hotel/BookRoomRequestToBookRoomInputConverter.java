package com.tinqinacademy.bff.core.converters.hotel;

import com.tinqinacademy.bff.api.operations.hotel.booksroomspecified.BookRoomRequest;
import com.tinqinacademy.myhotel.api.operations.booksroomspecified.BookRoomInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BookRoomRequestToBookRoomInputConverter implements Converter<BookRoomRequest, BookRoomInput> {
    @Override
    public BookRoomInput convert(BookRoomRequest source) {
        log.info("Start converting from BookRoomBFFInput to BookRoomInput");

        BookRoomInput output = BookRoomInput.builder()
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .phoneNo(source.getPhoneNo())
                .startDate(source.getStartDate())
                .endDate(source.getEndDate())
                .userId(source.getUserId())
                .build();

        log.info("End converting from BookRoomBFFInput to BookRoomInput");
        return output;
    }
}
