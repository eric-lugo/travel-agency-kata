package com.breadhardit.travelagencykata.application.command.command;

import com.breadhardit.travelagencykata.domain.Customer;
import com.breadhardit.travelagencykata.application.port.CustomersRepository;
import com.breadhardit.travelagencykata.infrastructure.rest.dto.PutCustomerDTO;
import lombok.Builder;
import lombok.SneakyThrows;
import lombok.Value;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Value
@Builder
public class CreateCustomerCommand {
    PutCustomerDTO customerInformation;
    CustomersRepository customersRepository;
    @SneakyThrows
    public String handle() {
        Customer customer = Customer.builder()
                .id(customerInformation.getId())
                .name(customerInformation.getName())
                .surnames(customerInformation.getSurnames())
                .birthDate(customerInformation.getBirthDate())
                .passportNumber(customerInformation.getPassportNumber())
                .enrollmentDate(LocalDate.now())
                .active(true)
                .build();
        customersRepository.saveCustomer(customer);
        return customer.getId();
    }

}
