package com.senacor.innolab.graalvm.integration.creditdetailservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RegisterForReflection
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditDetails {

    private Long id;
    private BigDecimal amount;
    private LocalDate start;
    private LocalDate end;
    private BigDecimal interestRate;

}
