package com.wadsonpontes.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    
    @JsonProperty("control_number")
    @Column(nullable = false, unique = true)
    private Long controlNumber;
    
    @JsonProperty("registration_date")
    @Column(nullable = false)
    @JsonDeserialize(using = MultiFormatLocalDateDeserializer.class)
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
    @Column(nullable = false)
    private Long customerCode;
    
    // Construtor, getters e setters gerados pelo Lombok
}
