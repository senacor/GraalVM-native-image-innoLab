package com.senacor.innolab.graalvm.integration.creditdetailservice;

import com.senacor.innolab.graalvm.integration.creditdetailservice.model.CreditDetails;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@RegisterRestClient(configKey = "credit-detail-service-api")
public interface CreditDetailService {
    @GET
    @Path("credit/{creditId}")
    CreditDetails getById(@PathParam("creditId") Long id);
}
