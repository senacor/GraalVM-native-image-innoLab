package de.senacor.innolab.graalvm.demo.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
public class CustomErrorResponse {
    private String error;
    private String details;
    private HttpStatus status;
    private Instant timestamp;
}
