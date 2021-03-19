package com.senacor.innolab.graalvm.credit.details.controller

import com.fasterxml.jackson.databind.JsonNode
import com.senacor.innolab.graalvm.credit.details.model.CreditDetails
import com.senacor.innolab.graalvm.credit.details.model.CreditDetailsDto
import com.senacor.innolab.graalvm.credit.details.service.CreditDetailsService
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.reactivex.Flowable
import org.slf4j.LoggerFactory
import java.math.BigDecimal
import java.time.LocalDate
import javax.annotation.PostConstruct
import javax.inject.Inject

@Controller("/credit")
class CreditController(@Inject private val creditDetailsService: CreditDetailsService) {

    private val logger = LoggerFactory.getLogger(javaClass.name)

    @PostConstruct
    fun postConstruct() {
        logger.info("Environment: ${System.getenv()}")

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
            creditDetailsService.save(it)
            logger.debug("Saved credit detail with ID ${it.id}")
        }
    }

    @Post("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun createCreditDetails(@Body dto: CreditDetailsDto): CreditDetails {
        logger.info("Creating credit details $dto")
        val saved = creditDetailsService.create(dto)
        logger.info("Saved credit details $saved")
        return saved
    }

    @Get("/{creditId}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getCreditDetails(creditId: String): CreditDetails? {
        logger.info("Looking up credit details with ID $creditId")
        val creditDetails = creditDetailsService.get(creditId)

        logger.info("Result: ${creditDetails ?: "none"}")
        return creditDetails
    }
}
