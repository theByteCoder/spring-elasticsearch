package com.spring.springelasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "customer", shards = 1, replicas = 0, refreshInterval = "10s", createIndex = true)
public class Customer {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
}
