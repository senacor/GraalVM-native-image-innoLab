package com.senacor.innolab.graalvm.credit.details.integration.interestrate

import io.micronaut.core.annotation.Introspected
import java.math.BigDecimal

@Introspected
data class InterestRateResponse(
    val requestId: String,
    val request: InterestRateRequest,
    val status: Status,
    val reason: String?,
    val interestRate: BigDecimal
) {
    companion object {
        enum class Status {
            ACCEPTED, DECLINED
        }
    }
}