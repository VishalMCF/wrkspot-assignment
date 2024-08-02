package com.example.asswrkspt.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer", indexes = {
    @Index(name = "idx_customer_firstname", columnList = "firstName"),
    @Index(name = "idx_customer_lastname", columnList = "lastName"),
    @Index(name = "idx_customer_mobile", columnList = "mobileNumber"),
    @Index(name = "idx_customer_spending_limit", columnList = "spendingLimit"),
    @Index(name = "idx_customer_age", columnList = "age")
})
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id", nullable = false)
    private Long customerId;

    @Column(name="first_name", nullable=false)
    private String firstName;

    @Column(name="last_name", nullable = false)
    private String lastName;

    @Column(name="age", nullable = false)
    private int age;

    @Column(name="spending_limit", nullable = false)
    private double spendingLimit;

    @Column(name = "mobile_number", nullable = false)
    private String mobileNumber;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "c_id")
    private List<AddressEntity> address;

    @Column(name = "createdAt", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updatedAt", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
