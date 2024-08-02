package com.example.asswrkspt.web.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.asswrkspt.service.CustomerService;
import com.example.asswrkspt.web.dto.request.CustomerQueryRequest;
import com.example.asswrkspt.web.dto.request.CustomerRequestDTO;
import com.example.asswrkspt.web.dto.response.CustomerResponseDTO;
import com.example.asswrkspt.web.dto.response.WorkSpotHttpResponse;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // added only for experimental purpose
    // not from the perspective of assignment
    @GetMapping("/{id}")
    public ResponseEntity<WorkSpotHttpResponse<CustomerResponseDTO>> getCustomerById(@PathVariable Long id) {
        return customerService.findById(id)
            .map(customer -> ResponseEntity.ok(
                WorkSpotHttpResponse.<CustomerResponseDTO>builder()
                    .data(customer)
                    .success(true)
                    .build()))
            .orElseGet(() -> ResponseEntity.status(404).body(
                WorkSpotHttpResponse.<CustomerResponseDTO>builder()
                    .message("entity not found")
                    .success(false)
                    .build()));
    }

    @GetMapping("/customers")
    public ResponseEntity<WorkSpotHttpResponse<List<CustomerResponseDTO>>> findCustomerByParams(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String city,
        @RequestParam(required = false) String state
    ) {
        return ResponseEntity.ok().body(
            WorkSpotHttpResponse.<List<CustomerResponseDTO>>builder()
                .success(true)
                .data(customerService.findCustomersByParams(
                    CustomerQueryRequest.builder().name(name).state(state).city(city).build()
                ).orElseGet(Collections::emptyList))
                .build()
        );

    }

    @PostMapping()
    public ResponseEntity<WorkSpotHttpResponse<CustomerResponseDTO>> createCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) {
        return customerService.createCustomer(customerRequestDTO)
            .map(customer -> ResponseEntity.ok(
                WorkSpotHttpResponse.<CustomerResponseDTO>builder()
                    .data(customer)
                    .success(true)
                    .build()))
            .orElseGet(() -> ResponseEntity.status(500).body(
                WorkSpotHttpResponse.<CustomerResponseDTO>builder()
                    .message("Error happened while executing request")
                    .success(false)
                    .build()));
    }
}
