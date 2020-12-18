package com.senacor.innolab.graalvm.web;

public class CheckResponse {
    private String checkResult;

    public CheckResponse() {
    }

    public CheckResponse(String checkResult) {
        this.checkResult = checkResult;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }
}
