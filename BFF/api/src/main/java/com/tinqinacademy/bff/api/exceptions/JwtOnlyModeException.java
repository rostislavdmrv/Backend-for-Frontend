package com.tinqinacademy.bff.api.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtOnlyModeException extends RuntimeException{
    public JwtOnlyModeException(String message) {
        super(message);
    }
}
