package com.example.asswrkspt.common.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.example.asswrkspt.model.entity.AddressEntity;
import com.example.asswrkspt.model.entity.CustomerEntity;
import com.example.asswrkspt.web.dto.request.AddressRequestDTO;
import com.example.asswrkspt.web.dto.request.CustomerRequestDTO;

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

}
