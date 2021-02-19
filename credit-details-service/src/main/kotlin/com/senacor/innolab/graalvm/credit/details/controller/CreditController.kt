package com.senacor.innolab.graalvm.credit.details.controller

import com.senacor.innolab.graalvm.credit.details.model.CreditDetails
import com.senacor.innolab.graalvm.credit.details.repository.CreditDetailsRepository
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.reactivex.Flowable
import org.slf4j.LoggerFactory

import java.util.*
import javax.annotation.PostConstruct
import javax.inject.Inject

@Controller("/credit") 
class CreditController(@Inject private val creditDetailsRepository: CreditDetailsRepository) {

    private val logger = LoggerFactory.getLogger(javaClass.name)

    @PostConstruct
    fun postConstruct() {
        Flowable.just(123_456, 100_000)
            .map { it.toLong()}
            .map { CreditDetails(identifier = it) }
            .forEach {
                creditDetailsRepository.save(it)
                logger.debug("Saved credit detail with ID ${it.identifier}")
            }
    }

    @Get("/{creditId}")
    @Produces(MediaType.APPLICATION_JSON) 
    fun getCreditDetails(creditId: String): CreditDetails? {
        logger.info("Looking up credit details with ID ${creditId.toLong()}")
        val creditDetails = creditDetailsRepository.findById(creditId.toLong()).unwrap()

        logger.info("Result: ${creditDetails ?: "none"}")
        return creditDetails
    }
}

fun <T> Optional<T>.unwrap(): T? = orElse(null)
