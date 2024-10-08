package com.wadsonpontes.repository;

import com.wadsonpontes.model.Customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByCode(Long code);
}

