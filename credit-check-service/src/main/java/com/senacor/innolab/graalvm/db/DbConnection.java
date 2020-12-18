package com.senacor.innolab.graalvm.db;

import com.senacor.innolab.graalvm.web.CheckRequest;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Values;
import org.neo4j.driver.types.Node;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class DbConnection {

    private Driver connector;

    public DbConnection(Driver connector) {
        this.connector = connector;
    }

    public void createNode(CheckRequest checkRequest) {
        connector.session()
                .run("CREATE (c:CheckRequest {customerId: $customerId}) RETURN c", Values.parameters("$customerId", checkRequest.getCustomerId()))
                .single();
    }

    public List<Node> getNodes(CheckRequest request) {
        return connector.session()
                .run("MATCH (n) RETURN n")
                .list()
                .stream()
                .map(record -> record.get("n").asNode())
                .collect(Collectors.toList());
    }
}
