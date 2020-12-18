package com.senacor.innolab.graalvm.service;

import com.senacor.innolab.graalvm.integration.creditdetailservice.CreditDetailService;
import com.senacor.innolab.graalvm.integration.creditdetailservice.model.CreditDetails;
import com.senacor.innolab.graalvm.integration.customerservice.CustomerService;
import com.senacor.innolab.graalvm.integration.customerservice.model.Customer;
import com.senacor.innolab.graalvm.integration.neo4j.DbConnection;
import com.senacor.innolab.graalvm.web.CheckRequest;
import com.senacor.innolab.graalvm.web.CheckResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Singleton;

@Singleton
public class CreditCheckService {

    private DbConnection dbConnection;

    private CustomerService customerService;

    private CreditDetailService creditDetailService;

    public CreditCheckService(DbConnection dbConnection,
                              @RestClient CustomerService customerService,
                              @RestClient CreditDetailService creditDetailService) {
        this.dbConnection = dbConnection;
        this.customerService = customerService;
        this.creditDetailService = creditDetailService;
    }

    public CheckResponse check(CheckRequest checkRequest){
        //Customer byId = customerService.getById(checkRequest.getCustomerId());
        //CreditDetails creditDetails = creditDetailService.getById(checkRequest.getCreditDetailId());
        // get all active records for the customer from neo
        // check if new credit can be added
        // approve/reject request and persist result
        //return result
        dbConnection.createNodes(checkRequest);
        return new CheckResponse("dummyId");
    }

}
