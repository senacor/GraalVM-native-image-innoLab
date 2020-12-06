package com.senacor.innolab.graalvm.service;

import com.senacor.innolab.graalvm.db.Neo4jConnector;
import com.senacor.innolab.graalvm.web.CheckRequest;
import com.senacor.innolab.graalvm.web.CheckResponse;
import org.neo4j.driver.types.Node;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreditCheckService {

    private Neo4jConnector neo4jConnector;

    public CreditCheckService(Neo4jConnector neo4jConnector) {
        this.neo4jConnector = neo4jConnector;
    }

    public CheckResponse check(CheckRequest checkRequest){
        //request customer id from customer service

        Node node = neo4jConnector.getNodes(checkRequest).get(0);
        ///calc rate
        //save calc rate
        //return calc rate
        return fromNode(node);
    }

    private CheckResponse fromNode(Node node){
        return new CheckResponse(node.get("name").asString());
    }
}
