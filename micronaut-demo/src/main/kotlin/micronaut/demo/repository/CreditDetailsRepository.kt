package micronaut.demo.repository

import javax.validation.constraints.NotNull;
import java.util.Optional;
import io.micronaut.data.repository.CrudRepository

import example.micronaut.model.CreditDetails
import io.micronaut.context.annotation.Executable

@Repository
interface CreditDetailsRepository: CrudRepository<CreditDetails, Long> {

    @Executable
    fun findById(@NotNull id: Long): Optional<CreditDetails>
}
