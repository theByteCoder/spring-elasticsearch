package com.spring.springelasticsearch.service;

import com.spring.springelasticsearch.dto.CustomerDTO;
import com.spring.springelasticsearch.entity.Customer;
import com.spring.springelasticsearch.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public ResponseEntity<CustomerDTO> add(CustomerDTO customerDTO) {
        String email = customerDTO.getEmail();
        if (customerRepository.findByEmail(email).isEmpty()) {
            Customer customer = mapDtoToEntity(customerDTO);
            customerRepository.save(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body(customerDTO);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    public ResponseEntity<List<CustomerDTO>> getAll() {
        List<Customer> customers = customerRepository.findAll();
        if (customers.size() > 0) {
            List<CustomerDTO> customerDTOS = customers
                    .stream().map(this::mapEntityToDto).collect(Collectors.toList());
            return ResponseEntity.ok(customerDTOS);
        }
        return ResponseEntity.ok(new ArrayList<>());
    }

    public ResponseEntity<List<CustomerDTO>> searchByFirstName(String firstName) {
        List<Customer> customers = customerRepository.findByFirstName(firstName);
        if (customers.size() > 0) {
            List<CustomerDTO> customerDTOS = customers
                    .stream().map(this::mapEntityToDto).collect(Collectors.toList());
            return ResponseEntity.ok(customerDTOS);
        }
        return ResponseEntity.ok(new ArrayList<>());
    }

    private CustomerDTO mapEntityToDto(Customer customer) {
        return CustomerDTO.builder()
                // .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .age(customer.getAge())
                .build();
    }

    private Customer mapDtoToEntity(CustomerDTO customerDTO) {
        return Customer.builder()
                // .id(customerDTO.getId())
                .firstName(customerDTO.getFirstName())
                .lastName(customerDTO.getLastName())
                .email(customerDTO.getEmail())
                .age(customerDTO.getAge())
                .build();
    }

}
