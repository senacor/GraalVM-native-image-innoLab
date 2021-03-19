package com.senacor.innolab.graalvm.credit.details.integration.customer

import com.senacor.innolab.graalvm.credit.details.model.CreditDetails
import io.micronaut.context.annotation.ConfigurationProperties
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.RxHttpClient
import io.reactivex.Flowable
import org.slf4j.LoggerFactory
import java.math.BigDecimal
import javax.annotation.PostConstruct
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class CustomerClient(
    @Inject private val config: CustomerClientConfig,
    @param:Client("/") private val httpClient: RxHttpClient
) {
    private val logger = LoggerFactory.getLogger(javaClass.name)

    fun getCustomer(customerId: Long): Customer {
        val url = "${config.url}/customer/$customerId"
        logger.info("Outgoing call to $url")
        val req: HttpRequest<Customer> = HttpRequest.GET(url)
        return httpClient.retrieve(req, Customer::class.java).blockingFirst()
    }
}

@ConfigurationProperties("client.customer")
data class CustomerClientConfig(var url: String? = null) {
    private val logger = LoggerFactory.getLogger(javaClass.name)

    @PostConstruct
    fun postConstruct() {
        url = "http://localhost:8080"
        logger.info("Initialized customer client config: $this")
    }
}