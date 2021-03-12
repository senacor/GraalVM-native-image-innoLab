package de.senacor.innolab.graalvm;

import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.senacor.innolab.graalvm.model.InterestRateRequest;
import de.senacor.innolab.graalvm.model.InterestRateResponse;
import de.senacor.innolab.graalvm.service.ProcessingService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Named;

@Slf4j
@Named("interest-rate")
public class InterestRateLambda implements RequestHandler<AwsProxyRequest, AwsProxyResponse> {

    @Inject
    ProcessingService service;

    @Inject
    ObjectMapper mapper;

    @SneakyThrows
    @Override
    public AwsProxyResponse handleRequest(AwsProxyRequest event, Context context) {
        log.info("Received event: " + mapper.writeValueAsString(event));

        InterestRateRequest request = mapper.readValue(event.getBody(), InterestRateRequest.class);
        InterestRateResponse response = service.process(request);
        response.setRequestId(context.getAwsRequestId());
        response.setRequest(request);

        String responseJson = mapper.writeValueAsString(response);
        log.info("Return response: " + responseJson);

        return new AwsProxyResponse(
                response.getStatus().getStatusCode(),
                null,
                responseJson);
    }
}
