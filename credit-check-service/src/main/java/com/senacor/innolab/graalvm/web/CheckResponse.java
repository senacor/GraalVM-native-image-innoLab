package com.senacor.innolab.graalvm.web;

public class CheckResponse {
    private String checkId;

    public CheckResponse() {
    }

    public CheckResponse(String checkId) {
        this.checkId = checkId;
    }

    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }
}
