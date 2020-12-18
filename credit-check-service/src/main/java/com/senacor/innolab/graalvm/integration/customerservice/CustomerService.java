package com.senacor.innolab.graalvm.integration.customerservice;

import com.senacor.innolab.graalvm.integration.customerservice.model.Customer;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@RegisterRestClient(configKey = "customer-service-api")
public interface CustomerService {
    @GET
    @Path("/customer/{customerId}")
    Customer getById(@PathParam("customerId") Long id);
}
