package com.senacor.innolab.graalvm.web;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RegisterForReflection

public class CheckResponse {
    @Schema(description = "The result of the credit check request", enumeration = {"APPROVED", "DECLINED"})
    private String checkResult;
}