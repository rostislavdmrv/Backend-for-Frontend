package com.tinqinacademy.bff.api.validations.bedsize;

import com.tinqinacademy.bff.api.models.enums.BedSize;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BedSizeValidator implements ConstraintValidator<BedSizeValidation, String> {
    private static final Set<String> VALID_BED_SIZES = EnumSet.allOf(BedSize.class).stream()
            .map(BedSize::getCode)
            .collect(Collectors.toSet());
    private boolean optional;

    @Override
    public void initialize(BedSizeValidation constraintAnnotation) {
        this.optional = constraintAnnotation.optional();
    }

    @Override
    public boolean isValid(String bedSize, ConstraintValidatorContext constraintValidatorContext) {
        if (optional && (bedSize == null || bedSize.isEmpty())) {
            return true;
        }

        if (bedSize == null || bedSize.isEmpty()) {
            return false;
        }

        return VALID_BED_SIZES.contains(bedSize);
    }
}
