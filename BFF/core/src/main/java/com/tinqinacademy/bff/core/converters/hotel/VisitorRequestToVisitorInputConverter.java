package com.tinqinacademy.bff.core.converters.hotel;

import com.tinqinacademy.bff.api.models.input.VisitorRequest;
import com.tinqinacademy.myhotel.api.models.input.VisitorInput;
import com.tinqinacademy.myhotel.api.operations.registersvisitors.RegisterVisitorInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Component
@Slf4j
public class VisitorRequestToVisitorInputConverter implements Converter<VisitorRequest, VisitorInput> {

    @Override
    public VisitorInput convert(VisitorRequest source) {
        log.info("Start converting from VisitorRequest to VisitorInput with input: {}",source);

        VisitorInput output = VisitorInput.builder()
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .birthdate(source.getBirthdate())
                .phoneNo(source.getPhoneNo())
                .idCardIssueAuthority(source.getIdCardIssueAuthority())
                .idCardIssueDate(source.getIdCardIssueDate())
                .idCardValidity(source.getIdCardValidity())
                .idCardNo(source.getIdCardNo())
                .build();

        log.info("End converting from VisitorRequest to VisitorInput with output: {}", output);
        return output;
    }
}
