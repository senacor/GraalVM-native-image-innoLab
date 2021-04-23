package de.senacor.innolab.graalvm.demo.customer.validation;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class AgeValidationRequest {
    private LocalDate dateOfBirth;
}
