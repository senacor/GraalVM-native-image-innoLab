package com.senacor.innolab.graalvm.credit.details.controller

import com.senacor.innolab.graalvm.credit.details.model.CreditDetails
import com.senacor.innolab.graalvm.credit.details.repository.CreditDetailsRepository
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.reactivex.Flowable

import java.util.*
import javax.annotation.PostConstruct
import javax.inject.Inject

@Controller("/credit") 
class CreditController(@Inject private val creditDetailsRepository: CreditDetailsRepository) {

    @PostConstruct
    fun postConstruct() {
        Flowable.just(123_456)
            .map { it.toLong()}
            .map { CreditDetails(identifier = it) }
            .forEach {
                creditDetailsRepository.save(it)
            }
    }

    @Get("/{creditId}")
    @Produces(MediaType.APPLICATION_JSON) 
    fun getCreditDetails(creditId: String): Optional<CreditDetails> {
        return creditDetailsRepository.findById(creditId.toLong())
    }
}
