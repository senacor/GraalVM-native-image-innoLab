package com.senacor.innolab.graalvm.integration.neo4j;

import com.senacor.innolab.graalvm.integration.neo4j.model.CreditDetailsNeo4j;
import com.senacor.innolab.graalvm.integration.neo4j.model.CustomerNeo4j;
import com.senacor.innolab.graalvm.integration.neo4j.model.Relation;
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

    public void createNodes(
            CustomerNeo4j customer,
            CreditDetailsNeo4j creditDetails,
            Relation relation
    ) {
        log.infov("create customer {0}", customer);
        CompletionStage<ResultCursor> customerGraphNOde = connector.asyncSession()
                .runAsync("MERGE (c:Customer {id: $id})",
                        Values.parameters("id", customer.getId())
                );

        log.infov("create credit {0}", creditDetails);
        CompletionStage<ResultCursor> creditGraphNode = connector.asyncSession()
                .runAsync("MERGE (c:Credit {id: $id})",
                        Values.parameters("id", creditDetails.getId()));

        CompletableFuture.allOf(customerGraphNOde.toCompletableFuture(), creditGraphNode.toCompletableFuture())
                .join();

        connector.asyncSession()
                .runAsync("MATCH (c:Customer),(a:Credit) " +
                                "WHERE c.id = $customerId AND a.id = $creditId " +
                                "MERGE (c)-[r:$relation]->(a) " +
                                "RETURN type(r)",
                        Values.parameters(
                                "customerId", customer.getId(),
                                "creditId", creditDetails.getId(),
                                "relation", relation.name()))
                .toCompletableFuture()
                .join();
    }
}
