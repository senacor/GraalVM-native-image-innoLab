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
    var start: LocalDate,

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    var end: LocalDate,

    @Column
    var interestRate: BigDecimal
) {

    companion object {
        fun of(id: String, amount: BigDecimal, startDate: String, endDate: String, interestRate: BigDecimal) =
            CreditDetails(
                id,
                amount,
                LocalDate.parse(startDate),
                LocalDate.parse(endDate),
                interestRate
            )
    }
}