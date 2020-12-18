package com.senacor.innolab.graalvm.integration.neo4j;

import com.senacor.innolab.graalvm.web.CheckRequest;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Values;
import org.neo4j.driver.async.ResultCursor;
import org.neo4j.driver.types.Node;

import javax.inject.Singleton;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@Singleton
public class DbConnection {

    private Driver connector;

    public DbConnection(Driver connector) {
        this.connector = connector;
    }

    public void createNodes(CheckRequest checkRequest) {
        CompletionStage<ResultCursor> customer = connector.asyncSession()
                .runAsync("MERGE (c:Customer {id: $id})",
                        Values.parameters("id", checkRequest.getCustomerId()));

        CompletionStage<ResultCursor> credit = connector.asyncSession()
                .runAsync("MERGE (c:Credit {id: $id})",
                        Values.parameters("id", checkRequest.getCreditDetailId()));


        CompletableFuture.allOf(customer.toCompletableFuture(),credit.toCompletableFuture())
                .join();

        connector.asyncSession()
                .runAsync("MATCH (c:Customer),(a:Credit) " +
                                "WHERE c.id = $customerId AND a.id = $creditId " +
                                "MERGE (c)-[r:APPROVED]->(a) " +
                                "RETURN type(r)",
                        Values.parameters("customerId", checkRequest.getCustomerId(), "creditId",checkRequest.getCreditDetailId()))
                .toCompletableFuture()
                .join();


    }
}
