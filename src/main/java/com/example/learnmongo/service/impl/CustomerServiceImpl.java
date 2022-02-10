package com.example.learnmongo.service.impl;

import com.example.learnmongo.entity.Customer;
import com.example.learnmongo.exceptions.BadRequestException;
import com.example.learnmongo.exceptions.ResourceAlreadyExists;
import com.example.learnmongo.exceptions.ResourceNotFoundException;
import com.example.learnmongo.repository.CustomerRepository;
import com.example.learnmongo.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Customer save(Customer customer) {
        Optional<Customer> byUsername = customerRepository.findByUsername(customer.getUsername());
        if (byUsername.isPresent()) {
            throw new ResourceAlreadyExists("User already exists with same username.");
        }
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomer(String username) {
        return customerRepository.findById(username)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void deleteCustomer(String username) {
        Customer customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        customerRepository.deleteById(customer.getId());
    }

    @Override
    public Customer patchCustomer(String username, Customer customer) {
        if (Objects.nonNull(customer.getUsername()) && !Objects.equals(username, customer.getUsername())) {
            throw new BadRequestException("Username cannot be updated using patch request." +
                    " Kindly overwrite the user using put method");
        }
        Customer customerInDb = customerRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        Update update = new Update();
        update.set("firstName", Objects.nonNull(customer.getFirstName()) ?
                customer.getFirstName() : customerInDb.getFirstName());
        update.set("lastName", Objects.nonNull(customer.getLastName()) ?
                customer.getLastName() : customerInDb.getLastName());
        update.set("modifiedOn", LocalDateTime.now());
        FindAndModifyOptions findAndModifyOptions = FindAndModifyOptions.options().returnNew(true);
        return mongoTemplate.findAndModify(query, update, findAndModifyOptions, Customer.class);
    }

    @Override
    public Customer updateCustomer(String username, Customer customer) {
        Customer customerInDb = customerRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        if (!Objects.equals(customer.getUsername(), customerInDb.getUsername())) {
            customerRepository.deleteById(customerInDb.getId());
        }
        return customerRepository.save(customer);
    }
}
