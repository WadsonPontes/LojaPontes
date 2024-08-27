package com.wadsonpontes.service;

import com.wadsonpontes.model.Customer;
import com.wadsonpontes.model.CustomerOrder;
import com.wadsonpontes.repository.CustomerOrderRepository;
import com.wadsonpontes.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerOrderService {
	@Autowired
	private CustomerRepository customerRepository;
	
    @Autowired
    private CustomerOrderRepository orderRepository;
    
    public CustomerOrder saveOrder(CustomerOrder request) {
    	if (orderRepository.existsByControlNumber(request.getControlNumber())) {
            throw new IllegalArgumentException("Control number already registered.");
        }
    	
        // Busque o cliente pelo código
        Customer customer = customerRepository.findByCode(request.getCustomerCode())
            .orElseThrow(() -> new RuntimeException("Customer not found"));

        CustomerOrder order = new CustomerOrder();
        order.setControlNumber(request.getControlNumber());
        order.setCustomerCode(customer.getCode());  // Associa o cliente ao pedido
        order.setName(request.getName());
        order.setQuantity(request.getQuantity() != null ? request.getQuantity() : 1);
        order.setRegistrationDate(request.getRegistrationDate() != null ? request.getRegistrationDate() : LocalDate.now());
        order.setUnitPrice(request.getUnitPrice());
        order.setTotalPrice(calculateTotalPrice(order));

        return orderRepository.save(order);
    }
    
    public List<CustomerOrder> saveOrders(List<CustomerOrder> request) {
    	List<CustomerOrder> orders = new ArrayList<CustomerOrder>();
    	
    	if (request.size() == 0) {
    		throw new IllegalArgumentException("Please submit a list with at least 1 item.");
    	}
    	
    	if (request.size() > 10) {
    		throw new IllegalArgumentException("Send a list with a maximum of 1 item.");
    	}
    	
    	for (CustomerOrder order : request) {
    		orders.add(saveOrder(order));
    	}
    	
    	return orders;
    }

    private Double calculateTotalPrice(CustomerOrder order) {
        double totalPrice = order.getUnitPrice() * order.getQuantity();
        if (order.getQuantity() > 10) {
            return totalPrice * 0.9;
        } else if (order.getQuantity() > 5) {
            return totalPrice * 0.95;
        }
        return totalPrice;
    }

    public List<CustomerOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<CustomerOrder> getByControlNumber(Long controlNumber) {
        return orderRepository.findByControlNumber(controlNumber);
    }

    public List<CustomerOrder> getByRegistrationDate(LocalDate registrationDate) {
        return orderRepository.findByRegistrationDate(registrationDate);
    }
}
