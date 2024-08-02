package com.example.asswrkspt.common.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.example.asswrkspt.model.entity.AddressEntity;
import com.example.asswrkspt.model.entity.CustomerEntity;
import com.example.asswrkspt.web.dto.request.AddressRequestDTO;
import com.example.asswrkspt.web.dto.request.CustomerRequestDTO;
import com.example.asswrkspt.web.dto.response.AddressResponseDTO;
import com.example.asswrkspt.web.dto.response.CustomerResponseDTO;

public class CustomerModelMapper {

    public static CustomerEntity mapToEntity(CustomerRequestDTO customerDTO) {
        CustomerEntity customer = new CustomerEntity();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setAge(customerDTO.getAge());
        customer.setSpendingLimit(customerDTO.getSpendingLimit());
        customer.setMobileNumber(customerDTO.getMobileNumber());

        List<AddressEntity> addresses = customerDTO
            .getAddress()
            .stream()
            .map((a) -> mapToEntity(a)).collect(Collectors.toList());
        customer.setAddress(addresses);

        return customer;
    }

    public static AddressEntity mapToEntity(AddressRequestDTO addressDTO) {
        AddressEntity address = new AddressEntity();
        address.setType(addressDTO.getType());
        address.setAddress1(addressDTO.getAddress1());
        address.setAddress2(addressDTO.getAddress2());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setZipCode(addressDTO.getZipCode());
        return address;
    }

    public static CustomerResponseDTO mapFromEntityToDTO(CustomerEntity customerEntity) {
        return CustomerResponseDTO.builder().customerId(customerEntity.getCustomerId())
            .age(customerEntity.getAge())
            .firstName(customerEntity.getFirstName())
            .lastName(customerEntity.getLastName())
            .address(customerEntity.getAddress()
                .stream()
                .map((a) -> mapFromEntityToDTO(a))
                .collect(Collectors.toUnmodifiableList()))
            .spendingLimit(customerEntity.getSpendingLimit())
            .mobileNumber(customerEntity.getMobileNumber())
            .build();
    }

    public static AddressResponseDTO mapFromEntityToDTO(AddressEntity addressEntity) {
        return AddressResponseDTO
            .builder()
            .id(addressEntity.getId())
            .state(addressEntity.getState())
            .city(addressEntity.getCity())
            .address1(addressEntity.getAddress1())
            .address2(addressEntity.getAddress2())
            .type(addressEntity.getType())
            .zipCode(addressEntity.getZipCode())
            .build();
    }

}
