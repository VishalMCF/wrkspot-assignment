package com.example.asswrkspt.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="address", indexes = {
    @Index(name = "idx_address_type", columnList = "address_type"),
    @Index(name = "idx_address_city", columnList = "city"),
    @Index(name = "idx_address_state", columnList = "state"),
    @Index(name = "idx_address_address1", columnList = "address_one"),
    @Index(name = "idx_address_address2", columnList = "address_two"),
    @Index(name = "idx_address_zipcode", columnList = "zip_code"),
})
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // it can be of enum too but leaving it as it is for now
    @Column(name="address_type", nullable=false)
    private String type;

    @Column(name="address_one", nullable=false)
    private String address1;

    @Column(name="address_two", nullable=false)
    private String address2;

    @Column(name="city", nullable=false)
    private String city;

    @Column(name="state", nullable=false)
    private String state;

    @Column(name="zip_code", nullable=false)
    private String zipCode;
}
