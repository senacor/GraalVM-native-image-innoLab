package com.senacor.innolab.graalvm.integration.customerservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RegisterForReflection
public class Customer {

    private Long id;

    private String firstNam;

    private String lastName;

    private LocalDate birthdate;

}
