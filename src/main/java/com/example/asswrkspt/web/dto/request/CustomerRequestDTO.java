package com.example.asswrkspt.web.dto.request;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.asswrkspt.web.validators.ValidCustomerRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ValidCustomerRequest
public class CustomerRequestDTO {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Min(value = 18, message = "Age must be greater than 18")
    private int age;

    @Min(value = 0, message = "Spending limit must be non-negative")
    private double spendingLimit;

    @NotNull
    @Min(value = 13, message= "Phone must containe number and country code both")
    @Max(value = 13, message= "Phone must containe number and country code both")
    private String mobileNumber;

    private List<AddressRequestDTO> address;
}
