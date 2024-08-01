package com.example.asswrkspt.web.dto.response;

import java.util.List;

import com.example.asswrkspt.web.dto.request.AddressRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDTO {
    private String customerId;
    private String firstName;
    private String lastName;
    private Integer age;
    private Double spendingLimit;
    private String mobileNumber;
    private List<AddressResponseDTO> address;
}
