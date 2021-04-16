package de.senacor.innolab.graalvm.demo.customer.service;

import de.senacor.innolab.graalvm.demo.customer.controller.openapiMock.model.CustomerDto;
import de.senacor.innolab.graalvm.demo.customer.exception.CustomerNotFoundException;
import de.senacor.innolab.graalvm.demo.customer.model.Customer;
import de.senacor.innolab.graalvm.demo.customer.model.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;


    public Collection<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public Customer get(long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    public Customer create(CustomerDto dto) {
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
        Optional.ofNullable(update.getDateOfBirth()).ifPresent(customer::setDateOfBirth);
        Optional.ofNullable(update.getIncome()).ifPresent(customer::setIncome);

        return customerRepository.save(customer);
    }

    public void delete(long customerId) {
        customerRepository.deleteById(customerId);
    }
}
