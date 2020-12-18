package com.senacor.innolab.graalvm.integration.creditdetailservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RegisterForReflection
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditDetails {

    private Long id;

}
