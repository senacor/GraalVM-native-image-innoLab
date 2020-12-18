package com.senacor.innolab.graalvm.service;

import com.senacor.innolab.graalvm.db.DbConnection;
import com.senacor.innolab.graalvm.web.CheckRequest;
import com.senacor.innolab.graalvm.web.CheckResponse;
import org.neo4j.driver.types.Node;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreditCheckService {

    private DbConnection dbConnection;

    public CreditCheckService(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public CheckResponse check(CheckRequest checkRequest){
        //request customer  from customer service
        //request credit from credit service
        // get all active records for the customer from neo
        // check if new credit can be added
        // approve/reject request and persist result
        //return result
        return new CheckResponse("dummyId");
    }

    private CheckResponse fromNode(Node node){
        return new CheckResponse(node.get("name").asString());
    }
}
