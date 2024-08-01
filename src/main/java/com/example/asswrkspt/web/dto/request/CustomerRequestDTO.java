package com.example.asswrkspt.web.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDTO {
    private String customerId;
    private String firstName;
    private String lastName;
    private int age;
    private double spendingLimit;
    private String mobileNumber;
    private List<AddressRequestDTO> address;
}
