package com.example.learnmongo.service.impl;

import com.example.learnmongo.entity.Customer;
import com.example.learnmongo.repository.CustomerReactiveRepository;
import com.example.learnmongo.service.CustomerReactiveService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Slf4j
public class CustomerReactiveServiceImpl implements CustomerReactiveService {

    @Autowired
    private CustomerReactiveRepository customerReactiveRepository;

    @Override
    public Flux<Customer> getAllCustomers() {
        Flux<Customer> customerFlux = customerReactiveRepository.findAll();
        customerFlux.subscribe(System.out::println);
        ObjectMapper objectMapper = new ObjectMapper();
        return customerFlux;
    }
}
