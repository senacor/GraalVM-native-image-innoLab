package de.senacor.innolab.graalvm.model;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.*;
import org.apache.http.HttpStatus;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RegisterForReflection
public class InterestRateResponse {

    @Getter
    @AllArgsConstructor
    public enum Status {
        ACCEPTED(HttpStatus.SC_OK), DECLINED(HttpStatus.SC_CONFLICT);

        private final int statusCode;
    }

    private String requestId;
    private InterestRateRequest request;
    private Status status;
    private String reason;
    private BigDecimal interestRate;
}
