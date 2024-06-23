package com.example.learnmongo.api;

import com.example.learnmongo.MongoDBTestContainerEnvironment;
import com.example.learnmongo.entity.Customer;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.MethodName.class)
@EnableAspectJAutoProxy
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerControllerTest extends MongoDBTestContainerEnvironment {

    @Autowired
    public WebTestClient webTestClient;

    @Test
    public void testSaveUser() {

        var customer = new Customer();
        customer.setUsername("testUsername");
        customer.setFirstName("Test");
        customer.setLastName("User");

        webTestClient.mutate().responseTimeout(Duration.ofMillis(50000)).build()
                .post().uri("v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(customer), Customer.class)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .consumeWith(response -> Assertions.assertNotNull(response.getResponseBody()));
    }

    @Test
    public void testDeleteUserNotFound() {

        webTestClient.delete().uri("v1/customers/12341qweqeq")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .is4xxClientError();
    }
}
