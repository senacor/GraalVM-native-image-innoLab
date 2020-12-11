package de.senacor.innolab.graalvm.customer.model;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Builder
@Entity
@Table(name = "CUSTOMER")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @NonNull
    @Column(name = "LASTNAME", nullable = false)
    private String lastName;

    @Column(name = "BIRTHDATE")
    private OffsetDateTime birthdate;
}
