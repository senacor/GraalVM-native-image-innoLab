package com.senacor.innolab.graalvm.web;

import javax.validation.constraints.NotNull;

public class CheckRequest {

    @NotNull(message = "must not be null")
    private Long customerId;
    @NotNull(message = "must not be null")
    private Long creditDetailId;

    public CheckRequest() {
    }

    public CheckRequest(Long customerId, Long creditDetailId) {
        this.customerId = customerId;
        this.creditDetailId = creditDetailId;
    }

    public Long getCreditDetailId() {
        return creditDetailId;
    }

    public void setCreditDetailId(Long creditDetailId) {
        this.creditDetailId = creditDetailId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
