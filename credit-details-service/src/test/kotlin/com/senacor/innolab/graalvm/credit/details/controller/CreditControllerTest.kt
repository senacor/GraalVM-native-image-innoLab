package com.senacor.innolab.graalvm.credit.details.controller

import com.senacor.innolab.graalvm.credit.details.integration.customer.Customer
import com.senacor.innolab.graalvm.credit.details.integration.customer.CustomerClient
import com.senacor.innolab.graalvm.credit.details.integration.interestrate.InterestRateClient
import com.senacor.innolab.graalvm.credit.details.integration.interestrate.InterestRateRequest
import com.senacor.innolab.graalvm.credit.details.integration.interestrate.InterestRateResponse
import com.senacor.innolab.graalvm.credit.details.model.CreditDetails
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDate
import javax.inject.Inject

@MicronautTest
class CreditControllerTest {

    @Inject
    @field:Client("/")
    lateinit var client: RxHttpClient

    @get:MockBean(CustomerClient::class)
    val customerClient = mockk<CustomerClient>()

    @get:MockBean(InterestRateClient::class)
    val interestRateClient = mockk<InterestRateClient>()

    @Test
    fun testGetCreditDetails() {
        every { customerClient.getCustomer(any()) } returns Customer(
            1,
            null,
            "Mustermann",
            LocalDate.of(1990, 1, 1),
            BigDecimal.valueOf(1500.0)
        )

        every { interestRateClient.calculateInterestRate(any()) } returns InterestRateResponse(
            "request_id",
            mockk(),
            InterestRateResponse.Companion.Status.ACCEPTED,
            null,
            BigDecimal.ONE
        )

        val request: HttpRequest<CreditDetails> = HttpRequest.GET("/credit/123456")
        val body = client.toBlocking().retrieve(request, CreditDetails::class.java)

        assertNotNull(body);
        assertEquals("123456", body.id);
    }

}
