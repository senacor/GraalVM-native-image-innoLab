package de.senacor.innolab.graalvm.customer.validation;

import de.senacor.innolab.graalvm.customer.validation.AgeValidationRequest;
import de.senacor.innolab.graalvm.customer.validation.config.ValidationConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Slf4j
@Service
@AllArgsConstructor
public class ValidationClient {

    private ValidationConfig config;
    private RestTemplate restTemplate;

    public boolean validate(LocalDate dateOfBirth) {
        log.info("Request age validation for " + dateOfBirth);
        try {
            restTemplate.postForEntity(config.getUrl() + "/validation/age",
                    new AgeValidationRequest(dateOfBirth),
                    Void.class);
        } catch (HttpClientErrorException e) {
            throw new ValidationException("The customer is not old enough", e);
        }
        return true;
    }

}
