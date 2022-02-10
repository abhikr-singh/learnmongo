package com.example.learnmongo.service;

import com.example.learnmongo.entity.Customer;

import java.util.List;

public interface CustomerService {
    public Customer save(Customer customer);

    public Customer getCustomer(String customerId);

    public List<Customer> getAllCustomers();

    public void deleteCustomer(String username);

    public Customer patchCustomer(String username, Customer customer);

    public Customer updateCustomer(String username, Customer customer);
}
