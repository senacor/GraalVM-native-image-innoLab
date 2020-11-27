package com.senacor.innolab.graalvm.service;

import com.senacor.innolab.graalvm.db.MongoConnector;
import com.senacor.innolab.graalvm.web.CheckRequest;
import com.senacor.innolab.graalvm.web.CheckResponse;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreditCheckService {

    private MongoConnector mongoConnector;

    public CreditCheckService(MongoConnector mongoConnector) {
        this.mongoConnector = mongoConnector;
    }

    public CheckResponse check(CheckRequest checkRequest){
        //request customer id from customer service
        ///calc rate
        //save calc rate
        //return calc rate
        return new CheckResponse("id");
    }
}
