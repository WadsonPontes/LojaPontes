package com.wadsonpontes.repository;

import com.wadsonpontes.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
    boolean existsByControlNumber(Long controlNumber);
    List<CustomerOrder> findByControlNumber(Long controlNumber);
    List<CustomerOrder> findByRegistrationDate(LocalDate registrationDate);
}
