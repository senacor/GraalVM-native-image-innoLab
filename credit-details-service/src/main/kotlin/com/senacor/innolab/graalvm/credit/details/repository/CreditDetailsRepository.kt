package com.senacor.innolab.graalvm.credit.details.repository

import javax.validation.constraints.NotNull;
import java.util.Optional;
import io.micronaut.data.repository.CrudRepository

import com.senacor.innolab.graalvm.credit.details.model.CreditDetails
import io.micronaut.context.annotation.Executable
import io.micronaut.data.annotation.Repository

@Repository
interface CreditDetailsRepository: CrudRepository<CreditDetails, Long> {

    @Executable
    override fun findById(@NotNull id: Long): Optional<CreditDetails>
}
