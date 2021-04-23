package de.senacor.innolab.graalvm.demo.validation.exception

import java.time.LocalDate

class DateOfBirthMissingException : IllegalArgumentException("Date of birth not given")

data class DateOfBirthWithing18YearsException(val dateOfBirth: LocalDate) : IllegalStateException() {
    override val message = "Date of birth ($dateOfBirth) is withing 18 years"
}