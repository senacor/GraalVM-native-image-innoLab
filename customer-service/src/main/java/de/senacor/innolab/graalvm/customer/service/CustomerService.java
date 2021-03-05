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
        validationClient.validate(Optional.ofNullable(dto.getDateOfBirth())
                .orElse(null));
        return customerRepository.save(Customer.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .dateOfBirth(dto.getDateOfBirth())
                .income(dto.getIncome())
                .build());
    }

    public Customer update(long customerId, CustomerDto update) {
        final Customer customer = get(customerId);

        Optional.ofNullable(update.getFirstName()).ifPresent(customer::setFirstName);
        Optional.ofNullable(update.getLastName()).ifPresent(customer::setLastName);
        Optional.ofNullable(update.getDateOfBirth())
                .map(dateOfBirth -> {
                    validationClient.validate(dateOfBirth);
                    return dateOfBirth;
                })
                .ifPresent(customer::setDateOfBirth);
        Optional.ofNullable(update.getIncome()).ifPresent(customer::setIncome);

        return customerRepository.save(customer);
    }

    public void delete(long customerId) {
        customerRepository.deleteById(customerId);
    }
}
