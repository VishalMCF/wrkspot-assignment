package com.example.asswrkspt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.asswrkspt.model.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    @Query("SELECT c FROM CustomerEntity c JOIN c.address a WHERE ((:firstName IS NULL OR c.firstName = :name) OR "
        + "(:lastName IS NULL OR c.lastName = :name))  "
        + "AND (:city IS NULL OR a.city = :city) "
        + "AND (:state IS NULL OR a.state = :state)")
    List<CustomerEntity> findCustomersByMultipleParams(@Param("name") String name, @Param("city") String city, @Param("state") String state);
}
