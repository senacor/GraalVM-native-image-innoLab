package de.senacor.innolab.graalvm.service;

import de.senacor.innolab.graalvm.model.CreditDetails;
import de.senacor.innolab.graalvm.model.CustomerDetails;
import de.senacor.innolab.graalvm.model.InterestRateRequest;
import de.senacor.innolab.graalvm.model.InterestRateResponse;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@ApplicationScoped
public class ProcessingService {

    public InterestRateResponse process(InterestRateRequest request) {
        int customerAge = calculateAge(request.getCustomerDetails());
        if (customerAge < 18) {
            return InterestRateResponse.builder()
                    .status(InterestRateResponse.Status.DECLINED)
                    .reason("Customer is younger than 18 years old.")
                    .build();
        }
        if (customerAge >= 65) {
            return InterestRateResponse.builder()
                    .status(InterestRateResponse.Status.DECLINED)
                    .reason("Customer is too old.")
                    .build();
        }
        if (BigDecimal.valueOf(1000).compareTo(request.getCustomerDetails().getIncome()) > 0) {
            return InterestRateResponse.builder()
                    .status(InterestRateResponse.Status.DECLINED)
                    .reason("Income is too low.")
                    .build();
        }

        BigDecimal interestRate = calculateInterestRate(request.getCustomerDetails(), request.getCreditDetails());
        if (BigDecimal.ZERO.compareTo(interestRate) >= 0) {
            return InterestRateResponse.builder()
                    .status(InterestRateResponse.Status.DECLINED)
                    .reason("Interest rate could not be determined")
                    .build();
        }

        return InterestRateResponse.builder()
                .status(InterestRateResponse.Status.ACCEPTED)
                .interestRate(interestRate)
                .build();
    }

    private int calculateAge(CustomerDetails customer) {
        return Period.between(customer.getDateOfBirth(), LocalDate.now()).getYears();
    }

    private BigDecimal calculateInterestRate(CustomerDetails customerDetails, CreditDetails creditDetails) {
        BigDecimal baseRate = BigDecimal.valueOf(calculateAge(customerDetails), 3);
        BigDecimal riskDuration = BigDecimal.valueOf(
                Period.between(creditDetails.getStart(), creditDetails.getEnd()).getMonths() + 1,
                4);
        BigDecimal riskAmount = BigDecimal.valueOf(
                creditDetails.getAmount().longValue() / customerDetails.getIncome().longValue(),
                4);

        return baseRate
                .add(riskAmount)
                .add(riskDuration);
    }
}
