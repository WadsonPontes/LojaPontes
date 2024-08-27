package com.wadsonpontes.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wadsonpontes.util.MultiFormatLocalDateDeserializer;

@Entity
@Table(name = "customer_orders")
@Data
@NoArgsConstructor
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private Long controlNumber;
    
    @Column(nullable = false)
    @JsonDeserialize(using = MultiFormatLocalDateDeserializer.class)
    private LocalDate registrationDate;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private Double unitPrice;
    
    @Column(nullable = false)
    private Integer quantity;
    
    @Column(nullable = false)
    private Double totalPrice;
    
    @Column(nullable = false)
    private Long customerCode;
    
    // Construtor, getters e setters gerados pelo Lombok
}
