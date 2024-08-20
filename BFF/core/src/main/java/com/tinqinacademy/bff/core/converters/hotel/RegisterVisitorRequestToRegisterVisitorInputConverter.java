package com.tinqinacademy.bff.core.converters.hotel;

import com.tinqinacademy.bff.api.operations.hotel.registersvisitors.RegisterVisitorRequest;
import com.tinqinacademy.myhotel.api.operations.registersvisitors.RegisterVisitorInput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RegisterVisitorRequestToRegisterVisitorInputConverter implements Converter<RegisterVisitorRequest, RegisterVisitorInput> {

    private final VisitorRequestToVisitorInputConverter visitorInputConverter;

    @Override
    public RegisterVisitorInput convert(RegisterVisitorRequest source) {
        log.info("Start converting from RegisterVisitorRequest to RegisterVisitorInput with input: {}", source);

        RegisterVisitorInput output = RegisterVisitorInput.builder()
                .visitorInputs(source.getVisitorInputs().stream()
                        .map(visitorInputConverter::convert)
                        .toList())
                .build();

        log.info("End converting from RegisterVisitorRequest to RegisterVisitorInput with output: {}", output);
        return output;
    }
}
