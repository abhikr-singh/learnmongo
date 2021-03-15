package com.example.learnmongo.service.impl;

import com.example.learnmongo.entity.Customer;
import com.example.learnmongo.exceptions.ResourceNotFoundException;
import com.example.learnmongo.repository.CustomerRepository;
import com.example.learnmongo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomer(String customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()) {
            throw new ResourceNotFoundException("Customer Not Found");
        }
        return optionalCustomer.get();
    }
}
