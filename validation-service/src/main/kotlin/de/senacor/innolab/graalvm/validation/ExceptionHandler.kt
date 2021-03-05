package de.senacor.innolab.graalvm.validation

import de.senacor.innolab.graalvm.validation.exception.DateOfBirthMissingException
import de.senacor.innolab.graalvm.validation.exception.DateOfBirthWithing18YearsException
import mu.KotlinLogging
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes
import org.springframework.boot.web.servlet.error.ErrorAttributes
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest
import java.time.Instant

data class CustomErrorResponse(val error: String, val status: HttpStatus, val timestamp: Instant) :
    DefaultErrorAttributes() {

    override fun getErrorAttributes(webRequest: WebRequest?, includeStackTrace: Boolean): MutableMap<String, Any>? {
        return super.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults())
            .apply {
                this["error"] = error
                this["status"] = status
                this["timestamp"] = timestamp
            }
    }
}


private val logger = KotlinLogging.logger { }


@ControllerAdvice
class ExceptionHandler {
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    fun handleDateOfBirthMissingException(ex: DateOfBirthMissingException, req: ServletWebRequest): ErrorAttributes =
        CustomErrorResponse(
            error = ex.message ?: "Date of birth was not given",
            status = HttpStatus.UNPROCESSABLE_ENTITY,
            timestamp = Instant.now()
        ).also { logger.error(ex) { it.error } }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(code = HttpStatus.PRECONDITION_FAILED)
    fun handleDateOfBirthWithin18YearsException(
        ex: DateOfBirthWithing18YearsException,
        req: ServletWebRequest
    ): ErrorAttributes =
        CustomErrorResponse(
            error = ex.message,
            status = HttpStatus.PRECONDITION_FAILED,
            timestamp = Instant.now()
        ).also { logger.error(ex) { it.error } }
}
