package de.senacor.innolab.graalvm.customer;

import de.senacor.innolab.graalvm.customer.exception.CustomerNotFoundException;
import de.senacor.innolab.graalvm.customer.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@Slf4j
@ControllerAdvice
public class CustomerServiceExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(
                CustomErrorResponse.builder()
                        .error(ex.getMessage())
                        .status(HttpStatus.NOT_FOUND)
                        .timestamp(Instant.now())
                        .build(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handleValidationException(ValidationException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(
                CustomErrorResponse.builder()
                        .error("Customer validation failed: " + ex.getMessage())
                        .details(ex.getDetails())
                        .status(HttpStatus.PRECONDITION_FAILED)
                        .timestamp(Instant.now())
                        .build(),
                HttpStatus.PRECONDITION_FAILED
        );
    }

}
