package de.senacor.innolab.graalvm.validation.controller

import de.senacor.innolab.graalvm.validation.service.AgeValidationService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
class ValidationController(val ageValidationService: AgeValidationService) {

    @RequestMapping(
        path = ["validation/age"],
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun validateAge(@RequestBody ageValidationRequest: AgeValidationRequest) {
        ageValidationService.validateAge(ageValidationRequest.dateOfBirth)
    }

}

data class AgeValidationRequest(val dateOfBirth: LocalDate?)