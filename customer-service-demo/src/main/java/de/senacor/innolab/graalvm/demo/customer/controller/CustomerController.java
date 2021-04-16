package de.senacor.innolab.graalvm.demo.customer.controller;

import de.senacor.innolab.graalvm.demo.customer.controller.openapiMock.api.CustomerApi;
import de.senacor.innolab.graalvm.demo.customer.controller.openapiMock.model.CustomerDto;
import de.senacor.innolab.graalvm.demo.customer.mapper.CustomerMapper;
import de.senacor.innolab.graalvm.demo.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class CustomerController implements CustomerApi {

    private CustomerMapper customerMapper;
    private CustomerService customerService;

    @Override
    public ResponseEntity<List<CustomerDto>> getAllCustomer() {
        return ResponseEntity.ok(
                customerService.getAllCustomer().stream()
                        .map(customerMapper::toDto)
                        .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<CustomerDto> getCustomer(Long customerId) {
        return ResponseEntity.ok(customerMapper.toDto(customerService.get(customerId)));
    }

    @Override
    public ResponseEntity<CustomerDto> createCustomer(CustomerDto customer) {
        return ResponseEntity.ok(
                customerMapper.toDto(
                        customerService.create(customer))
        );
    }

    @Override
    public ResponseEntity<CustomerDto> updateCustomer(Long customerId, CustomerDto customer) {
        return ResponseEntity.ok(
                customerMapper.toDto(
                        customerService.update(customerId, customer))
        );
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(Long customerId) {
        customerService.delete(customerId);
        return ResponseEntity.noContent().build();
    }
}
