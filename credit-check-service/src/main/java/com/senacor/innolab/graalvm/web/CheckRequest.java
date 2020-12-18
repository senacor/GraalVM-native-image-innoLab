package com.senacor.innolab.graalvm.web;

import javax.validation.constraints.NotBlank;

public class CheckRequest {
    @NotBlank(message = "missing customer id")
    private String customerId;
    @NotBlank(message = "missing credit detail id")
    private String creditDetailId;

    public CheckRequest() {
    }

    public CheckRequest(String customerId, String creditDetailId) {
        this.customerId = customerId;
        this.creditDetailId = creditDetailId;
    }

    public String getCreditDetailId() {
        return creditDetailId;
    }

    public void setCreditDetailId(String creditDetailId) {
        this.creditDetailId = creditDetailId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
