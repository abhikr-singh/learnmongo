package com.example.learnmongo.repository;

import com.example.learnmongo.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    public Optional<Customer> findByUsername(String username);

}
