package com.senacor.innolab.graalvm.web;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@RegisterForReflection

public class CheckRequest {

    @NotNull(message = "must not be null")
    @Min(0)
    private Long customerId;
    @NotNull(message = "must not be null")
    @Min(0)
    private Long creditDetailId;


}
