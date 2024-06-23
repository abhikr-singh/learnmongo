package com.example.learnmongo.api;

import com.example.learnmongo.annotations.Loggable;
import com.example.learnmongo.entity.Customer;
import com.example.learnmongo.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/")
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @GetMapping("health")
    public ResponseEntity<String> health() {
        return new ResponseEntity<>("HEALTHY", HttpStatus.OK);
    }


    @PostMapping(value = "customers")
    @Loggable
    public ResponseEntity<Customer> saveCustomer(@Valid @RequestBody Customer customer) {
        var customerDb = customerService.save(customer);
        return new ResponseEntity<>(customerDb, HttpStatus.CREATED);
    }

    @GetMapping(value = "customers/{username}")
    @Loggable
    public ResponseEntity<Customer> getCustomer(@PathVariable(value = "username") String username) {
        var customer = customerService.getCustomer(username);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PatchMapping(value = "customers/{username}")
    @Loggable
    public ResponseEntity<Customer> patchCustomer(@PathVariable(value = "username") String username,
                                                  @RequestBody Customer customer) {
        var updatedCustomer = customerService.patchCustomer(username, customer);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @PutMapping(value = "customers/{username}")
    @Loggable
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "username") String username,
                                                   @RequestBody Customer customer) {
        var updatedCustomer = customerService.updateCustomer(username, customer);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);

    }

    @DeleteMapping(value = "customers/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Loggable
    public ResponseEntity<Object> deleteCustomer(@PathVariable(value = "username") String username) {
        customerService.deleteCustomer(username);
        var jsonObject = new JSONObject();
        jsonObject.put("message", JSONObject.stringToValue(String.format("Deleted user with username: %s", username)));
        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
    }

    @GetMapping(value = "customers")
    @Loggable
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

}
