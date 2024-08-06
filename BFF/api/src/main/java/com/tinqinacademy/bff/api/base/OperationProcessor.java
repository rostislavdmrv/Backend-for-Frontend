package com.tinqinacademy.bff.api.base;

import com.tinqinacademy.bff.api.models.errors.ErrorWrapper;
import io.vavr.control.Either;

public interface OperationProcessor <I extends OperationRequest, O extends OperationResponse>{
    Either<ErrorWrapper,O> process(I input);
}
