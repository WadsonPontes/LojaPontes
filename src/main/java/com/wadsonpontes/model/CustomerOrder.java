package com.wadsonpontes.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "customer_orders")
@Data
@NoArgsConstructor
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @JsonProperty("control_number")
    @Column(nullable = false, unique = true)
    private Long controlNumber;
    
    @JsonProperty("registration_date")
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDate registrationDate;
    
    @JsonProperty("name")
    @Column(nullable = false)
    private String name;
    
    @JsonProperty("unit_price")
    @Column(nullable = false)
    private Double unitPrice;
    
    @JsonProperty("quantity")
    @Column(nullable = false)
    private Integer quantity;
    
    @JsonProperty("total_price")
    @Column(nullable = false)
    private Double totalPrice;
    
    @JsonProperty("customer_code")
    @Column(nullable = false, unique = true)
    private Long customerCode;
    
    // Construtor, getters e setters gerados pelo Lombok
}
