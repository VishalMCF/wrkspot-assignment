package com.example.asswrkspt.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestDTO {
    private String type;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zipCode;
}
