package com.example.asswrkspt.service.impl;

import jakarta.transaction.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.asswrkspt.common.logging.Sink;
import com.example.asswrkspt.common.mappers.CustomerModelMapper;
import com.example.asswrkspt.messaging.MessageService;
import com.example.asswrkspt.model.entity.CustomerEntity;
import com.example.asswrkspt.model.event.EventMessage;
import com.example.asswrkspt.repository.CustomerRepository;
import com.example.asswrkspt.service.CustomerService;
import com.example.asswrkspt.web.dto.request.CustomerQueryRequest;
import com.example.asswrkspt.web.dto.request.CustomerRequestDTO;
import com.example.asswrkspt.web.dto.response.CustomerResponseDTO;
import com.example.asswrkspt.web.exceptions.WrkSpotCommonException;
import com.example.asswrkspt.web.exceptions.enums.CommonErrorCode;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final MessageService messageService;

    private final Sink sink;

    public CustomerServiceImpl(CustomerRepository customerRepository,
        MessageService messageService, Sink sink) {
        this.customerRepository = customerRepository;
        this.messageService = messageService;
        this.sink = sink;
    }

    @Override
    public Optional<CustomerResponseDTO> findById(Long id) {
        return customerRepository.findById(id).map(CustomerModelMapper::mapFromEntityToDTO);

    }

    @Override
    @Transactional
    public Optional<CustomerResponseDTO> createCustomer(CustomerRequestDTO customerRequestDTO) {
        try {
            CustomerEntity customerEntity = CustomerModelMapper.mapToEntity(customerRequestDTO);
            customerEntity = customerRepository.save(customerEntity);
            CustomerResponseDTO customerResponseDTO = CustomerModelMapper.mapFromEntityToDTO(customerEntity);
            messageService.sendMessage(new EventMessage<>(customerResponseDTO));
            return Optional.of(customerResponseDTO);
        } catch (Exception exc) {
            sink.error("Could not update customer", CommonErrorCode.CREATE_ENTITY_ERROR);
            throw new WrkSpotCommonException(exc, CommonErrorCode.CREATE_ENTITY_ERROR);
        }
    }

    @Override
    public Optional<List<CustomerResponseDTO>> findCustomersByParams(CustomerQueryRequest query) {
        List<CustomerEntity> customerEntities = customerRepository.findCustomersByMultipleParams(
            query.getName(), query.getCity(), query.getState()
        );
        return CollectionUtils.isEmpty(customerEntities) ? Optional.empty() : Optional.of(customerEntities.stream()
                .map(CustomerModelMapper::mapFromEntityToDTO)
                .collect(Collectors.toList()));
    }
}
