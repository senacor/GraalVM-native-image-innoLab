package com.senacor.innolab.graalvm.web;

import com.senacor.innolab.graalvm.service.CreditCheckService;
import lombok.extern.jbosslog.JBossLog;
import org.jboss.logging.Logger;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/credit-check")
@JBossLog
public class CreditCheckResource {

    private CreditCheckService creditCheckService;

    public CreditCheckResource(CreditCheckService creditCheckService) {
        this.creditCheckService = creditCheckService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CheckResponse checkCredit(@Valid CheckRequest checkRequest) {
        log.infov("received Request {0}", checkRequest);
        return creditCheckService.check(checkRequest);
    }
}
