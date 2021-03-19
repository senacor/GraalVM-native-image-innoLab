package com.senacor.innolab.graalvm.credit.details.integration.customer

import java.math.BigDecimal
import java.time.LocalDate

data class Customer(
    val id: Long,
    val firstName: String?,
    val lastName: String,
    val dateOfBirth: LocalDate,
    val income: BigDecimal
) {
    fun getName() = if (!firstName.isNullOrEmpty()) "$firstName $lastName" else lastName
}