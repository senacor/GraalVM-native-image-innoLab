package com.senacor.innolab.graalvm.credit.details.controller

import com.senacor.innolab.graalvm.credit.details.model.CreditDetails
import com.senacor.innolab.graalvm.credit.details.repository.CreditDetailsRepository
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.reactivex.Flowable
import org.slf4j.LoggerFactory
import java.math.BigDecimal

import java.util.*
import javax.annotation.PostConstruct
import javax.inject.Inject

@Controller("/credit")
class CreditController(@Inject private val creditDetailsRepository: CreditDetailsRepository) {

    private val logger = LoggerFactory.getLogger(javaClass.name)

    @PostConstruct
    fun postConstruct() {
        Flowable.just(
            CreditDetails.of(
                "123456",
                BigDecimal.valueOf(123456),
                "2020-01-01",
                "2020-12-31",
                BigDecimal.valueOf(1)
            ),
            CreditDetails.of(
                "111111",
                BigDecimal.valueOf(111111),
                "2019-01-01",
                "2019-12-31",
                BigDecimal.valueOf(11, 1)
            )
        ).forEach {
            creditDetailsRepository.save(it)
            logger.debug("Saved credit detail with ID ${it.id}")
        }
    }

    @Get("/{creditId}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getCreditDetails(creditId: String): CreditDetails? {
        logger.info("Looking up credit details with ID $creditId")
        val creditDetails = creditDetailsRepository.findById(creditId).unwrap()

        logger.info("Result: ${creditDetails ?: "none"}")
        return creditDetails
    }
}

fun <T> Optional<T>.unwrap(): T? = orElse(null)
