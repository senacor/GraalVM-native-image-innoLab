package com.senacor.innolab.graalvm.credit.details.integration.interestrate

import com.senacor.innolab.graalvm.credit.details.integration.customer.Customer
import io.micronaut.context.annotation.ConfigurationProperties
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import org.slf4j.LoggerFactory
import javax.annotation.PostConstruct
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class InterestRateClient(
    @Inject private val config: InterestRateClientConfig,
    @param:Client("/") private val httpClient: RxHttpClient
) {
    private val logger = LoggerFactory.getLogger(javaClass.name)

    fun calculateInterestRate(request: InterestRateRequest): InterestRateResponse {
        val url = "${config.url}/interest-rate"
        logger.info("Outgoing call to $url with body $request")
        val req: HttpRequest<InterestRateRequest> = HttpRequest.POST(url, request)
        return httpClient.retrieve(req, InterestRateResponse::class.java).blockingFirst()
    }
}

@ConfigurationProperties("client.interest-rate")
data class InterestRateClientConfig(var url: String? = null)