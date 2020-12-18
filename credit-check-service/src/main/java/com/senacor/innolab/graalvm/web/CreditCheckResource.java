package com.senacor.innolab.graalvm.web;

import com.senacor.innolab.graalvm.service.CreditCheckService;
import org.jboss.logging.Logger;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/credit-check")
public class CreditCheckResource {
    private static final Logger LOGGER = Logger.getLogger(CreditCheckResource.class);

    private CreditCheckService creditCheckService;

    public CreditCheckResource(CreditCheckService creditCheckService) {
        this.creditCheckService = creditCheckService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CheckResponse checkCredit(@Valid CheckRequest checkRequest) {
        LOGGER.infov("received Request {0}", checkRequest);
        return creditCheckService.check(checkRequest);
    }
}
