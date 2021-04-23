package de.senacor.innolab.graalvm.demo.validation.controller

import de.senacor.innolab.graalvm.demo.validation.service.AgeValidationService
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

private val logger = KotlinLogging.logger {}

@RestController
class ValidationController(val ageValidationService: AgeValidationService) {

    @RequestMapping(
        path = ["validation/age"],
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun validateAge(@RequestBody ageValidationRequest: AgeValidationRequest) {
        logger.info { "Received age validation request for ${ageValidationRequest.dateOfBirth}" }
        ageValidationService.validateAge(ageValidationRequest.dateOfBirth)
    }

}

data class AgeValidationRequest(val dateOfBirth: LocalDate?)