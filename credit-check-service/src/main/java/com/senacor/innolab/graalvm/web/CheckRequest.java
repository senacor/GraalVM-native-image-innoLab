package com.senacor.innolab.graalvm.web;

public class CheckRequest {
    private String customerId;

    public CheckRequest() {
    }

    public CheckRequest(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
