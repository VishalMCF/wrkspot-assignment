package com.example.asswrkspt.web.dto.response;

import java.util.List;

import com.example.asswrkspt.web.dto.request.AddressRequestDTO;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponseDTO{
    private long customerId;
    private String firstName;
    private String lastName;
    private Integer age;
    private Double spendingLimit;
    private String mobileNumber;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<AddressResponseDTO> address;
}
