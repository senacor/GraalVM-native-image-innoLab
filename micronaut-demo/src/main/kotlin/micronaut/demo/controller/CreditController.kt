package micronaut.demo.controller

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.reactivex.Flowable

import micronaut.demo.model.CreditDetails
import micronaut.demo.repository.CreditDetailsRepository
import java.util.*
import javax.annotation.PostConstruct
import javax.inject.Inject

@Controller("/credit") 
class CreditController(@Inject private val creditDetailsRepository: CreditDetailsRepository) {

    @PostConstruct
    fun postConstruct() {
        Flowable.just(123_456)
            .map { it.toLong()}
            .map { CreditDetails(it) }
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
