package com.senacor.innolab.graalvm.credit.details.service

import com.senacor.innolab.graalvm.credit.details.integration.customer.CustomerClient
import com.senacor.innolab.graalvm.credit.details.integration.interestrate.CustomerDetails
import com.senacor.innolab.graalvm.credit.details.integration.interestrate.InterestRateClient
import com.senacor.innolab.graalvm.credit.details.integration.interestrate.InterestRateRequest
import com.senacor.innolab.graalvm.credit.details.integration.interestrate.InterestRateResponse
import com.senacor.innolab.graalvm.credit.details.model.CreditDetails
import com.senacor.innolab.graalvm.credit.details.model.CreditDetailsDto
import com.senacor.innolab.graalvm.credit.details.repository.CreditDetailsRepository
import org.slf4j.LoggerFactory
import java.lang.IllegalArgumentException
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class CreditDetailsService(
    @Inject private val creditDetailsRepository: CreditDetailsRepository,
    @Inject private val customerClient: CustomerClient,
    @Inject private val interestRateClient: InterestRateClient
) {
    private val logger = LoggerFactory.getLogger(javaClass.name)

    fun create(dto: CreditDetailsDto): CreditDetails {
        logger.info("Request customer with id ${dto.customerId}")
        val customer = customerClient.getCustomer(dto.customerId)
        logger.info("Get customer $customer")

        val interestRateRequest = InterestRateRequest(
            CustomerDetails(
                customer.getName(),
                customer.dateOfBirth,
                customer.income
            ),
            com.senacor.innolab.graalvm.credit.details.integration.interestrate.CreditDetails(
                dto.amount,
                dto.start,
                dto.end
            )
        )
        logger.info("Request interest rate with $interestRateRequest")
        val response = interestRateClient.calculateInterestRate(interestRateRequest)
        logger.info("Get interest rate $response")
        if (response.status != InterestRateResponse.Companion.Status.ACCEPTED) {
            throw IllegalArgumentException("Interest rate was not approved")
        }

        val creditDetails = CreditDetails.of(
            Random.nextLong(0, 100000).toString(),
            dto.amount,
            dto.start,
            dto.end,
            response.interestRate
        )

        return creditDetailsRepository.save(creditDetails)
    }

    fun save(creditDetails: CreditDetails): CreditDetails {
        return creditDetailsRepository.save(creditDetails)
    }

    fun get(creditId: String): CreditDetails? {
        return creditDetailsRepository.findById(creditId).unwrap()
    }
}

fun <T> Optional<T>.unwrap(): T? = orElse(null)