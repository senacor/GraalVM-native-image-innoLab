package example.micronaut.model

import io.micronaut.core.annotation.Introspected;
import javax.persistence.*

@Entity()
@Table(name = "credit")
@Introspected
data class CreditDetails(@Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long)
