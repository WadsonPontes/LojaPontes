package com.wadsonpontes.controller;

import com.wadsonpontes.model.CustomerOrder;
import com.wadsonpontes.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService orderService;

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public CustomerOrder createOrder(@RequestBody CustomerOrder order) {
        return orderService.saveOrder(order);
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<CustomerOrder> getOrders(@RequestParam(required = false) Long controlNumber,
                                 @RequestParam(required = false) String registrationDate) {
        if (controlNumber != null) {
            return orderService.getByControlNumber(controlNumber);
        } else if (registrationDate != null) {
            return orderService.getByRegistrationDate(LocalDate.parse(registrationDate));
        } else {
        	return orderService.getAllOrders();
        }
    }
}
