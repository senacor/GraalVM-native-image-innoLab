package de.senacor.innolab.graalvm;

import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.senacor.innolab.graalvm.model.CreditDetails;
import de.senacor.innolab.graalvm.model.CustomerDetails;
import de.senacor.innolab.graalvm.model.InterestRateRequest;
import de.senacor.innolab.graalvm.model.InterestRateResponse;
import io.quarkus.amazon.lambda.test.LambdaClient;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

@QuarkusTest
public class InterestRateLambdaTest {

    @Inject
    public ObjectMapper mapper;

    @Test
    public void testReadRequest() throws IOException {
        AwsProxyRequest request = mapper.readValue(
                new File("src/test/resources/event.json"),
                AwsProxyRequest.class);

        Assertions.assertNotNull(request);
        Assertions.assertNotNull(request.getBody());

        InterestRateRequest payload = mapper.readValue(request.getBody(), InterestRateRequest.class);
        Assertions.assertNotNull(payload);
        Assertions.assertNotNull(payload.getCustomerDetails());
        Assertions.assertNotNull(payload.getCreditDetails());
    }

    @Test
    public void testHandleRequest() throws JsonProcessingException {
        InterestRateRequest payload = InterestRateRequest.builder()
                .customerDetails(CustomerDetails.builder()
                        .name("test")
                        .dateOfBirth(LocalDate.of(1990, 1, 1))
                        .income(BigDecimal.valueOf(1500))
                        .build())
                .creditDetails(CreditDetails.builder()
                        .amount(BigDecimal.valueOf(10000))
                        .start(LocalDate.of(2021, 1, 1))
                        .end(LocalDate.of(2021, 12, 31))
                        .build())
                .build();

        AwsProxyRequest request = new AwsProxyRequest();
        request.setBody(mapper.writeValueAsString(payload));

        AwsProxyResponse out = LambdaClient.invoke(AwsProxyResponse.class, request);

        Assertions.assertNotNull(out);
        Assertions.assertEquals(200, out.getStatusCode());
        Assertions.assertNotNull(out.getBody());

        InterestRateResponse response = mapper.readValue(out.getBody(), InterestRateResponse.class);

        Assertions.assertEquals(InterestRateResponse.Status.ACCEPTED, response.getStatus());
        Assertions.assertTrue(response.getInterestRate().compareTo(BigDecimal.ZERO) > 0);
    }
}
