package com.breadhardit.travelagencykata.infrastructure.persistence.repository;

import com.breadhardit.travelagencykata.application.port.CustomersRepository;
import com.breadhardit.travelagencykata.domain.Customer;
import com.breadhardit.travelagencykata.infrastructure.persistence.entity.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Scope("singleton")
@RequiredArgsConstructor

public class CustomersInDataBase implements CustomersRepository {
    private final CustomersJPARepository customersJPARepository;
    @Override
    public void saveCustomer(Customer customer) {
        customersJPARepository.save(
                CustomerEntity.builder()
                        .id(customer.getId())
                        .name(customer.getName())
                        .surnames(customer.getSurnames())
                        .birthDate(customer.getBirthDate())
                        .passportNumber(customer.getPassportNumber())
                        .enrollmentDate(customer.getEnrollmentDate())
                        .active(customer.getActive())
                        .build()
        );
    }

    @Override
    public Optional<Customer> getCustomerById(String id) {
        Optional<CustomerEntity> searchedCustomer = customersJPARepository.findById(id);
        if (searchedCustomer.isPresent()){
            CustomerEntity customerEntity = searchedCustomer.get();
            Optional<Customer> optionalCustomer = Optional.of(
                    Customer.builder()
                            .id(customerEntity.getId())
                            .name(customerEntity.getName())
                            .surnames(customerEntity.getSurnames())
                            .birthDate(customerEntity.getBirthDate())
                            .passportNumber(customerEntity.getPassportNumber())
                            .enrollmentDate(customerEntity.getEnrollmentDate())
                            .active(customerEntity.getActive())
                            .build()
            );
                return optionalCustomer;
        }else return Optional.empty();
    }

    @Override
    public Optional<Customer> getCustomerByPassport(String id) {
        Optional<CustomerEntity> searchedCustomer = customersJPARepository.getByPassportNumber(id);
        if (searchedCustomer.isPresent()){
            CustomerEntity customerEntity = searchedCustomer.get();
            Optional<Customer> optionalCustomer = Optional.of(
                    Customer.builder()
                            .id(customerEntity.getId())
                            .name(customerEntity.getName())
                            .surnames(customerEntity.getSurnames())
                            .birthDate(customerEntity.getBirthDate())
                            .passportNumber(customerEntity.getPassportNumber())
                            .enrollmentDate(customerEntity.getEnrollmentDate())
                            .active(customerEntity.getActive())
                            .build()
            );
            return optionalCustomer;
        }else return Optional.empty();
    }
}
