package de.senacor.innolab.graalvm.customer.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(long id) {
        super(String.format("Customer with id %d was not found.", id));
    }
}
