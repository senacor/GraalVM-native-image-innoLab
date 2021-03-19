package com.senacor.innolab.graalvm.credit.details.integration.interestrate

import java.math.BigDecimal
import java.time.LocalDate

data class InterestRateRequest(val customerDetails: CustomerDetails, val creditDetails: CreditDetails)

data class CustomerDetails(val name: String, val dateOfBirth: LocalDate, val income: BigDecimal)

data class CreditDetails(val amount: BigDecimal, val start: LocalDate, val end: LocalDate)