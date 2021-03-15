package com.example.learnmongo.service;

import com.example.learnmongo.entity.Customer;

public interface CustomerService {
    public Customer save(Customer customer);

    public Customer getCustomer(String customerId);
}
