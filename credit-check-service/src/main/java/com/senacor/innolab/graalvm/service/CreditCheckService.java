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

    private final DbConnection dbConnection;
    private final CustomerService customerService;

    private final CreditDetailService creditDetailService;

    public CreditCheckService(DbConnection dbConnection,
                              @RestClient CustomerService customerService,
                              @RestClient CreditDetailService creditDetailService) {
        this.dbConnection = dbConnection;
        this.customerService = customerService;
        this.creditDetailService = creditDetailService;
    }

    public CheckResponse check(CheckRequest checkRequest){
        Customer customer = customerService.getById(checkRequest.getCustomerId());
        //CreditDetails creditDetails = creditDetailService.getById(checkRequest.getCreditDetailId());
        CreditDetails creditDetails = CreditDetails.builder() //TODO remove hardcoded value once the credit detail service is available
                .id(checkRequest.getCreditDetailId())
                .build();

        dbConnection.createNodes(customer,creditDetails);
        return CheckResponse.builder()
                .checkResult("APPROVED")
                .build();
    }

}
