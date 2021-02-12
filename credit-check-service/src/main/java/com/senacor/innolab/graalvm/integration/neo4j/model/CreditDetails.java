package com.senacor.innolab.graalvm.integration.neo4j.model;

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
public class CreditDetails {
    private String Id;
    private BigDecimal amount;
    private LocalDate startTime;
    private LocalDate endTime;
    private Long fixedInterestRateInYears;
}
