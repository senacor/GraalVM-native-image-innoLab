package com.senacor.innolab.graalvm.integration.neo4j;

import com.senacor.innolab.graalvm.integration.creditdetailservice.model.CreditDetails;
import com.senacor.innolab.graalvm.integration.customerservice.model.Customer;
import lombok.extern.jbosslog.JBossLog;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Values;
import org.neo4j.driver.async.ResultCursor;

import javax.inject.Singleton;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Singleton
@JBossLog
public class DbConnection {

    private final Driver connector;

    public DbConnection(Driver connector) {
        this.connector = connector;
    }

    public void createNodes(Customer customer, CreditDetails creditDetails) {
        log.infov("create customer {}", customer);
        CompletionStage<ResultCursor> customerGraphNOde = connector.asyncSession()
                .runAsync("MERGE (c:Customer {" +
                                "id: $id, " +
                                "firstName: $firstName , " +
                                "lastName: $lastName " +
                                "})",
                        Values.parameters(
                                "id", customer.getId(),
                                "firstName", customer.getFirstName(),
                                "lastName", customer.getLastName())
                );

        log.infov("create credit {}", creditDetails);
        CompletionStage<ResultCursor> creditGraphNode = connector.asyncSession()
                .runAsync("MERGE (c:Credit {id: $id})",
                        Values.parameters("id", creditDetails.getId()));


        CompletableFuture.allOf(customerGraphNOde.toCompletableFuture(), creditGraphNode.toCompletableFuture())
                .join();

        connector.asyncSession()
                .runAsync("MATCH (c:Customer),(a:Credit) " +
                                "WHERE c.id = $customerId AND a.id = $creditId " +
                                "MERGE (c)-[r:APPROVED]->(a) " +
                                "RETURN type(r)",
                        Values.parameters("customerId", customer.getId(), "creditId", creditDetails.getId()))
                .toCompletableFuture()
                .join();


    }
}
