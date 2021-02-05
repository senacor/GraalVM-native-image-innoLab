package com.senacor.innolab.graalvm.credit.details.model

import javax.persistence.*

@Entity
@Table(name = "credit")
class CreditDetails(

    @Id
    @Column(nullable = false)
    var identifier: Long
)
