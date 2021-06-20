package com.example.learnmongo.api;

import com.example.learnmongo.entity.Customer;
import com.example.learnmongo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{version}/health")
    public ResponseEntity<String> health() {
        return new ResponseEntity<>("HEALTHY", HttpStatus.OK);
    }
    
    @PostMapping(value="/{version}/customers")
    public ResponseEntity<Customer>  saveCustomer(@RequestBody Customer customer) {
        Customer customerDb = customerService.save(customer);
        return new ResponseEntity<>(customerDb, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{version}/customers/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable(value = "customerId") String customerId) {
        Customer customer = customerService.getCustomer(customerId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
    
}
