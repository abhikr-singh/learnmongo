package com.example.learnmongo.api;


import com.example.learnmongo.annotations.Loggable;
import com.example.learnmongo.entity.Customer;
import com.example.learnmongo.service.CustomerReactiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/v2/")
@Slf4j
public class CustomerReactiveController {

    @Autowired
    private CustomerReactiveService customerReactiveService;

    @GetMapping(value = "/customers", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @Loggable
    public Flux<Customer> getAllCustomers() {
        return customerReactiveService.getAllCustomers();
    }
}
