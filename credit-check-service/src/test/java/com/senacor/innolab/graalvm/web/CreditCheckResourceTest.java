package com.senacor.innolab.graalvm.web;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class CreditCheckResourceTest {


    @Test
    public void POST_checkCreditWithCustomerIdNullReturns400() {
        CheckRequest requestWithoutCustomerId = new CheckRequest(null,"id");

        given()
                .when()
                .body(requestWithoutCustomerId)
                .contentType(ContentType.JSON)
                .post("/credit-check")
                .then()
                .statusCode(400)
                .body(is("{\"parameterViolations\":[{\"constraintType\":\"PARAMETER\",\"path\":\"checkCredit.checkRequest.customerId\",\"message\":\"missing customer id\"}]}"));
    }

    @Test
    public void POST_checkCreditWithCustomerIdBlankReturns400() {
        CheckRequest requestWithoutCustomerId = new CheckRequest("","id");

        given()
                .when()
                .body(requestWithoutCustomerId)
                .contentType(ContentType.JSON)
                .post("/credit-check")
                .then()
                .statusCode(400)
                .body(is("{\"parameterViolations\":[{\"constraintType\":\"PARAMETER\",\"path\":\"checkCredit.checkRequest.customerId\",\"message\":\"missing customer id\"}]}"));
    }

    @Test
    public void POST_checkCreditWithCreditIdBlankReturns400() {
        CheckRequest requestWithoutCustomerId = new CheckRequest("id","");

        given()
                .when()
                .body(requestWithoutCustomerId)
                .contentType(ContentType.JSON)
                .post("/credit-check")
                .then()
                .statusCode(400)
                .body(is("{\"parameterViolations\":[{\"constraintType\":\"PARAMETER\",\"path\":\"checkCredit.checkRequest.creditDetailId\",\"message\":\"missing credit detail id\"}]}"));
    }

    @Test
    public void POST_checkCreditWithCreditIdNullReturns400() {
        CheckRequest requestWithoutCustomerId = new CheckRequest("id",null);

        given()
                .when()
                .body(requestWithoutCustomerId)
                .contentType(ContentType.JSON)
                .post("/credit-check")
                .then()
                .statusCode(400)
                .body(is("{\"parameterViolations\":[{\"constraintType\":\"PARAMETER\",\"path\":\"checkCredit.checkRequest.creditDetailId\",\"message\":\"missing credit detail id\"}]}"));
    }

    @Test
    public void POST_checkCreditWithValidRequestReturns200() {
        CheckRequest requestWithoutCustomerId = new CheckRequest("id","anotherId");

        given()
                .when()
                .body(requestWithoutCustomerId)
                .contentType(ContentType.JSON)
                .post("/credit-check")
                .then()
                .statusCode(200)
                .body(is("{\"checkResult\":\"dummyId\"}"));
    }
}