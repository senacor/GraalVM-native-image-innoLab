package micronaut.demo.model

import io.micronaut.core.annotation.Introspected;
import javax.persistence.*

@Entity
@Table(name = "credit")
@Introspected
class CreditDetails() {

    @Id
    var id: Long = -1

    constructor(id: Long) : this() {
        this.id = id
    }
}
