package com.senacor.innolab.graalvm.credit.details.controller

import com.senacor.innolab.graalvm.credit.details.model.CreditDetails
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
class CreditControllerTest {

    @Inject
    @field:Client("/")
    lateinit var client: RxHttpClient

    @Test
    fun testGetCreditDetails() {
        val request: HttpRequest<CreditDetails> = HttpRequest.GET("/credit/123456")
        val body = client.toBlocking().retrieve(request, CreditDetails::class.java)

        assertNotNull(body);
        assertEquals("123456", body.id);
    }
}
