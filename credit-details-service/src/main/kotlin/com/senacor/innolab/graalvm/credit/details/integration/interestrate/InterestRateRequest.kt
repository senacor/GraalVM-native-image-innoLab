package com.senacor.innolab.graalvm.credit.details.integration.interestrate

import io.micronaut.core.annotation.Introspected
import java.math.BigDecimal
import java.time.LocalDate

@Introspected
data class InterestRateRequest(val customerDetails: CustomerDetails, val creditDetails: CreditDetails)

@Introspected
data class CustomerDetails(val name: String, val dateOfBirth: LocalDate, val income: BigDecimal)

@Introspected
data class CreditDetails(val amount: BigDecimal, val start: LocalDate, val end: LocalDate)