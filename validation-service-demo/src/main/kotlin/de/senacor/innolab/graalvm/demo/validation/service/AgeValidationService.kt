package de.senacor.innolab.graalvm.demo.validation.service

import arrow.core.flatMap
import arrow.core.left
import arrow.core.right
import arrow.core.rightIfNotNull
import de.senacor.innolab.graalvm.demo.validation.exception.DateOfBirthMissingException
import de.senacor.innolab.graalvm.demo.validation.exception.DateOfBirthWithing18YearsException
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class AgeValidationService {

    fun validateAge(dateOfBirth: LocalDate?): Boolean =
        dateOfBirth.rightIfNotNull { DateOfBirthMissingException() }
            .flatMap {
                if (it.isBefore(LocalDate.now().minusYears(18)))
                    it.right() else
                    DateOfBirthWithing18YearsException(it).left()
            }.fold({ throw it }, { true })

}