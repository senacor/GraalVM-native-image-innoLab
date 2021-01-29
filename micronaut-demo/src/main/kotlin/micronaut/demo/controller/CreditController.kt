package example.micronaut.controller

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces

import example.micronaut.model.CreditDetails
import micronaut.demo.repository.CreditDetailsRepository

@Controller("/credit") 
class CreditController(private val creditDetailsRepository: CreditDetailsRepository) {

    @Get("/{creditId}")  
    @Produces(MediaType.APPLICATION_JSON) 
    fun index(creditId: String): CreditDetails {
        return creditDetailsRepository.findById(creditId.toLong()).get()
    }
}
