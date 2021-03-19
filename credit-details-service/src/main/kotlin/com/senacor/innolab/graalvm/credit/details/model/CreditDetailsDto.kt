package com.senacor.innolab.graalvm.credit.details.model

import io.micronaut.core.annotation.Introspected
import java.math.BigDecimal
import java.time.LocalDate

@Introspected
data class CreditDetailsDto(
    val customerId: Long,
    val amount: BigDecimal,
    val start: LocalDate,
    val end: LocalDate
)