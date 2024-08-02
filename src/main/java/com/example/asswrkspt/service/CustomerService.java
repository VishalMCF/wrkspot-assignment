package com.example.asswrkspt.service;

import java.util.List;
import java.util.Optional;

import com.example.asswrkspt.web.dto.request.CustomerQueryRequest;
import com.example.asswrkspt.web.dto.request.CustomerRequestDTO;
import com.example.asswrkspt.web.dto.response.CustomerResponseDTO;

public interface CustomerService {

    Optional<CustomerResponseDTO> findById(Long id);

    Optional<CustomerResponseDTO> createCustomer(CustomerRequestDTO customerRequestDTO);

    Optional<List<CustomerResponseDTO>> findCustomersByParams(CustomerQueryRequest customerQueryRequest);

}
