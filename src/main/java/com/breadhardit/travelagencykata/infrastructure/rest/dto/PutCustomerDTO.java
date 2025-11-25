package com.breadhardit.travelagencykata.infrastructure.rest.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
public class PutCustomerDTO {
    String id = UUID.randomUUID().toString();
    String name;
    String surnames;
    LocalDate birthDate;
    String passportNumber;
    Boolean active;
}
