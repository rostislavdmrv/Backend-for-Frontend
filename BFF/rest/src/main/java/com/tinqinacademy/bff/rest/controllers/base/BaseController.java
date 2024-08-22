package com.tinqinacademy.bff.rest.controllers.base;

import com.tinqinacademy.bff.api.base.OperationResponse;
import com.tinqinacademy.bff.api.models.errors.ErrorWrapper;
import com.tinqinacademy.bff.api.models.usertoken.CustomUser;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

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

    protected CustomUser getUserFromContext() {
        CustomUser principal = (CustomUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        return principal;
    }
}
