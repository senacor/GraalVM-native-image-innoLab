package de.senacor.innolab.graalvm.customer.service;

import de.senacor.innolab.graalvm.customer.controller.openapiMock.model.CustomerDto;
import de.senacor.innolab.graalvm.customer.exception.CustomerNotFoundException;
import de.senacor.innolab.graalvm.customer.model.Customer;
import de.senacor.innolab.graalvm.customer.model.CustomerRepository;
import de.senacor.innolab.graalvm.customer.validation.ValidationClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;
    private ValidationClient validationClient;

    public Collection<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public Customer get(long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    public Customer create(CustomerDto dto) {
        validationClient.validate(Optional.ofNullable(dto.getBirthdate())
                .map(OffsetDateTime::toLocalDate)
                .orElse(null));
        return customerRepository.save(Customer.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .birthdate(dto.getBirthdate())
                .build());
    }

    public Customer update(long customerId, CustomerDto update) {
        final Customer customer = get(customerId);

        Optional.ofNullable(update.getFirstName()).ifPresent(customer::setFirstName);
        Optional.ofNullable(update.getLastName()).ifPresent(customer::setLastName);
        Optional.ofNullable(update.getBirthdate())
                .map(dateOfBirth -> {
                    validationClient.validate(dateOfBirth.toLocalDate());
                    return dateOfBirth;
                })
                .ifPresent(customer::setBirthdate);

        return customerRepository.save(customer);
    }

    public void delete(long customerId) {
        customerRepository.deleteById(customerId);
    }
}
