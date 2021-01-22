package com.senacor.innolab.graalvm.web;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@RegisterForReflection
public class CheckRequest {

    @NotNull(message = "must not be null")
    private Long customerId;
    @NotNull(message = "must not be null")
    private Long creditDetailId;


}
