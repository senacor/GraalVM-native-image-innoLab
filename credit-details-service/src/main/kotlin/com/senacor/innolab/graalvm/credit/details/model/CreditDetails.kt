package com.senacor.innolab.graalvm.credit.details.model

import javax.persistence.*

@Entity
@Table(name = "credit")
class CreditDetails(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var identifier: Long
)
