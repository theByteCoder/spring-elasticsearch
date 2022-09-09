package com.spring.springelasticsearch.controller;

import com.spring.springelasticsearch.dto.CustomerDTO;
import com.spring.springelasticsearch.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rest/elastic/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDTO> addCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.add(customerDTO);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return customerService.getAll();
    }

    @GetMapping("/{firstName}")
    public ResponseEntity<List<CustomerDTO>> searchCustomersByFirstName(@PathVariable @NonNull String firstName) {
        return customerService.searchByFirstName(firstName);
    }
}
