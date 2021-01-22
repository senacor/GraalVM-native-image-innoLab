package com.senacor.innolab.graalvm.service;

import com.senacor.innolab.graalvm.integration.creditdetailservice.CreditDetailService;
import com.senacor.innolab.graalvm.integration.creditdetailservice.model.CreditDetails;
import com.senacor.innolab.graalvm.integration.customerservice.CustomerService;
import com.senacor.innolab.graalvm.integration.customerservice.model.Customer;
import com.senacor.innolab.graalvm.integration.neo4j.DbConnection;
import com.senacor.innolab.graalvm.web.CheckRequest;
import com.senacor.innolab.graalvm.web.CheckResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CreditCheckServiceTest {

    private CreditCheckService underTest;
    private DbConnection mockConnection;
    private CustomerService mockCustomerService;
    private CreditDetailService mockCreditDetailService;


    @BeforeEach
    void setUp() {
        mockConnection = mock(DbConnection.class);
        mockCustomerService = mock(CustomerService.class);
        mockCreditDetailService = mock(CreditDetailService.class);

        underTest = new CreditCheckService(mockConnection, mockCustomerService, mockCreditDetailService);
    }

    @Test
    void checkFetchesCustomerAndCreditDetails() {

        long creditDetailId = 2L;
        long customerId = 1L;
        underTest.check(CheckRequest.builder()
                .customerId(customerId)
                .creditDetailId(creditDetailId)
                .build());

        verify(mockCustomerService).getById(customerId);
        //verify(mockCreditDetailService).getById(creditDetailId); TODO comment in once the credit details servie implementation is available
    }


    @Test
    void checkWritesCustomerAndCreditDetailsFromServiceCallToDB() {
        long creditDetailId = 2L;
        long customerId = 1L;

        Customer testCustomer = Customer
                .builder()
                .id(3L)
                .firstName("foo")
                .lastName("bar")
                .build();
        CreditDetails testCreditDetails = CreditDetails.builder()
                .id(2L) //TODO change once the credit detail service is implemented
                .build();

        when(mockCustomerService.getById(customerId)).thenReturn(testCustomer);

        //when(mockCreditDetailService.getById(creditDetailId)).thenReturn(testCreditDetails);

        underTest.check(CheckRequest.builder()
                .customerId(customerId)
                .creditDetailId(creditDetailId)
                .build());

        verify(mockConnection).createNodes(testCustomer, testCreditDetails);

    }

    @Test
    void checkAlwaysReturnsApprove() {
        Assertions.assertThat(underTest.check(CheckRequest.builder()
                .customerId(1L)
                .creditDetailId(1L)
                .build()))
                .isEqualTo(CheckResponse.builder()
                        .checkResult("APPROVED")
                        .build());


    }
}