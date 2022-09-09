package com.spring.springelasticsearch.repository;

import com.spring.springelasticsearch.entity.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends ElasticsearchRepository<Customer, Long> {

    @Override
    List<Customer> findAll();

    Optional<Customer> findByEmail(String email);

    List<Customer> findByFirstName(String firstName);

    List<Customer> findByLastName(String lastName);
}
