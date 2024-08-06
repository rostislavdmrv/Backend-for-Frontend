package com.tinqinacademy.bff.rest.controllers.base;

import com.tinqinacademy.bff.api.base.OperationResponse;
import com.tinqinacademy.bff.api.models.errors.ErrorWrapper;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public  abstract  class BaseController {
    protected ResponseEntity<?> handle(Either<ErrorWrapper, ? extends OperationResponse> output) {
        if (output.isLeft()) {
            return error(output);
        }
        return new ResponseEntity<>(output.get(), HttpStatus.OK);
    }

    protected ResponseEntity<?> handleWithStatus(Either<ErrorWrapper, ? extends OperationResponse> output, HttpStatus status) {
        if (output.isLeft()) {
            return error(output);
        }
        return new ResponseEntity<>(output.get(), status);
    }

    private ResponseEntity<?> error(Either<ErrorWrapper, ? extends OperationResponse> output) {
        ErrorWrapper errorWrapper = output.getLeft();
        return new ResponseEntity<>(errorWrapper.getErrors(), errorWrapper.getErrorHttpStatus());
    }
}
