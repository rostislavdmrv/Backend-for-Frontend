package com.tinqinacademy.bff.api.exceptions;

import com.tinqinacademy.bff.api.models.errors.ErrorsResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BffValidationException extends RuntimeException {
    private final List<ErrorsResponse> violations;

    public BffValidationException(List<ErrorsResponse> violations) {
        this.violations = violations;
    }
}
