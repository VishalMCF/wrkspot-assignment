package com.example.asswrkspt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.asswrkspt.model.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<Long, CustomerEntity> {

}
