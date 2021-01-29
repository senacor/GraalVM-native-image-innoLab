package micronaut.demo.repository

import javax.validation.constraints.NotNull;
import java.util.Optional;
import io.micronaut.data.repository.CrudRepository

import micronaut.demo.model.CreditDetails
import io.micronaut.context.annotation.Executable
import io.micronaut.data.annotation.Repository
import javax.annotation.PostConstruct

@Repository
interface CreditDetailsRepository: CrudRepository<CreditDetails, Long> {

    @Executable
    override fun findById(@NotNull id: Long): Optional<CreditDetails>
}
