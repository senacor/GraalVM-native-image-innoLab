package com.senacor.innolab.graalvm.credit.details.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "credit")
class CreditDetails(

    @Id
    @Column(nullable = false)
    var id: String,

    @Column
    var amount: BigDecimal,

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    var startDate: LocalDate,

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    var endDate: LocalDate,

    @Column
    var fixedInterestRateInYears: Double
) {

    companion object {
        fun of(id: String, amount: Double, startDate: String, endDate: String, fixedInterestRateInYears: Double) =
            CreditDetails(
                id,
                BigDecimal.valueOf(amount),
                LocalDate.parse(startDate),
                LocalDate.parse(endDate),
                fixedInterestRateInYears
            )
    }
}