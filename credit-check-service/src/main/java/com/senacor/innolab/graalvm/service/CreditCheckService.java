package com.senacor.innolab.graalvm.service;

import com.senacor.innolab.graalvm.integration.creditdetailservice.CreditDetailService;
import com.senacor.innolab.graalvm.integration.creditdetailservice.model.CreditDetails;
import com.senacor.innolab.graalvm.integration.customerservice.CustomerService;
import com.senacor.innolab.graalvm.integration.customerservice.model.Customer;
import com.senacor.innolab.graalvm.integration.neo4j.DbConnection;
import com.senacor.innolab.graalvm.integration.neo4j.model.CreditDetailsNeo4j;
import com.senacor.innolab.graalvm.integration.neo4j.model.CustomerNeo4j;
import com.senacor.innolab.graalvm.integration.neo4j.model.Relation;
import com.senacor.innolab.graalvm.web.CheckRequest;
import com.senacor.innolab.graalvm.web.CheckResponse;
import lombok.extern.jbosslog.JBossLog;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Singleton;

@Singleton
@JBossLog
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

    public CheckResponse check(CheckRequest checkRequest) {
        Customer customer = customerService.getById(checkRequest.getCustomerId());
        log.infov("received customer {0}", customer);

        CreditDetails creditDetails = creditDetailService.getById(checkRequest.getCreditDetailId());
        log.infov("received creditDetails {0}", creditDetails);

        Relation result = evaluateCredit(customer, creditDetails);
        log.infov("evaluated credit as {0}", result);

        dbConnection.createNodes(
                new CustomerNeo4j(String.valueOf(customer.getId())),
                new CreditDetailsNeo4j(String.valueOf(creditDetails.getId())),
                result
        );

        return CheckResponse.builder()
                .checkResult(result.name())
                .build();
    }

    private Relation evaluateCredit(Customer customer, CreditDetails creditDetails) {
        return Relation.APPROVED;
    }
}
