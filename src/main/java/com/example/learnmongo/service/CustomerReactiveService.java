package com.example.learnmongo.service;

import com.example.learnmongo.entity.Customer;
import reactor.core.publisher.Flux;

public interface CustomerReactiveService {
    Flux<Customer> getAllCustomers();
}
