package com.example.learnmongo;

import org.springframework.context.annotation.Profile;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import jakarta.annotation.PreDestroy;

@Profile("test")
@Testcontainers
public class MongoDBTestContainerEnvironment implements AutoCloseable {

    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest")
            .withExposedPorts(27017)
            .withReuse(true);

    static {
        mongoDBContainer.start();
        var mappedPort = mongoDBContainer.getMappedPort(27017);
        System.setProperty("mongodb.container.port", String.valueOf(mappedPort));
        // Add shutdown hook to ensure cleanup
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (mongoDBContainer != null && mongoDBContainer.isRunning()) {
                mongoDBContainer.stop();
            }
        }));
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        System.out.println("=================== " + mongoDBContainer.getReplicaSetUrl() + "================ "
                + mongoDBContainer.getHost());
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @PreDestroy
    @Override
    public void close() {
        if (mongoDBContainer != null && mongoDBContainer.isRunning()) {
            mongoDBContainer.close();
        }
    }
}
