package com.example.asswrkspt.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressResponseDTO {
    private Long id;
    private String type;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zipCode;
}
