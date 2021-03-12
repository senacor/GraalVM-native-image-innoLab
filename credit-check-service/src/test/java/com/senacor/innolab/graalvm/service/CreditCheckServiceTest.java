package com.senacor.innolab.graalvm.service;

import com.senacor.innolab.graalvm.integration.creditdetailservice.CreditDetailService;
import com.senacor.innolab.graalvm.integration.creditdetailservice.model.CreditDetails;
import com.senacor.innolab.graalvm.integration.customerservice.CustomerService;
import com.senacor.innolab.graalvm.integration.customerservice.model.Customer;
import com.senacor.innolab.graalvm.integration.neo4j.DbConnection;
import com.senacor.innolab.graalvm.integration.neo4j.model.CreditDetailsNeo4j;
import com.senacor.innolab.graalvm.integration.neo4j.model.CustomerNeo4j;
import com.senacor.innolab.graalvm.integration.neo4j.model.Relation;
import com.senacor.innolab.graalvm.web.CheckRequest;
import com.senacor.innolab.graalvm.web.CheckResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.Mockito.*;

class CreditCheckServiceTest {

    private CreditCheckService underTest;
    private DbConnection mockConnection;
    private CustomerService mockCustomerService;
    private CreditDetailService mockCreditDetailService;

    private final long customerId = 1L;
    private final long creditDetailsId = 2L;

    @BeforeEach
    void setUp() {
        mockConnection = mock(DbConnection.class);
        mockCustomerService = mock(CustomerService.class);
        mockCreditDetailService = mock(CreditDetailService.class);

        underTest = new CreditCheckService(mockConnection, mockCustomerService, mockCreditDetailService);

        when(mockCustomerService.getById(customerId)).thenReturn(
                Customer.builder()
                        .id(customerId)
                        .firstName("foo")
                        .lastName("bar")
                        .dateOfBirth(LocalDate.of(1990, 1, 1))
                        .income(BigDecimal.valueOf(2500))
                        .build());

        when(mockCreditDetailService.getById(creditDetailsId)).thenReturn(
                CreditDetails.builder()
                        .id(creditDetailsId)
                        .amount(BigDecimal.valueOf(10000))
                        .start(LocalDate.of(2020, 1, 1))
                        .end(LocalDate.of(2022, 12, 31))
                        .interestRate(BigDecimal.valueOf(125, 2))
                        .build());
    }

    @Test
    void checkFetchesCustomerAndCreditDetails() {
        underTest.check(CheckRequest.builder()
                .customerId(customerId)
                .creditDetailId(creditDetailsId)
                .build());

        verify(mockCustomerService).getById(customerId);
        verify(mockCreditDetailService).getById(creditDetailsId);
    }


    @Test
    void checkWritesCustomerAndCreditDetailsFromServiceCallToDB() {
        underTest.check(CheckRequest.builder()
                .customerId(customerId)
                .creditDetailId(creditDetailsId)
                .build());

        verify(mockConnection).createNodes(
                new CustomerNeo4j(String.valueOf(customerId)),
                new CreditDetailsNeo4j(String.valueOf(creditDetailsId)),
                Relation.APPROVED
        );
    }

    @Test
    void checkAlwaysReturnsApprove() {
        Assertions.assertThat(underTest.check(CheckRequest.builder()
                .customerId(customerId)
                .creditDetailId(creditDetailsId)
                .build()))
                .isEqualTo(CheckResponse.builder()
                        .checkResult("APPROVED")
                        .build());
    }
}