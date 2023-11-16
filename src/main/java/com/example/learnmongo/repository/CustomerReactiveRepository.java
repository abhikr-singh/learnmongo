package com.example.learnmongo.repository;

import com.example.learnmongo.entity.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerReactiveRepository extends ReactiveMongoRepository<Customer, String> {
}
