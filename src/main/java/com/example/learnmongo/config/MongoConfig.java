package com.example.learnmongo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {
    /*
     * Factory bean that creates the com.mongodb.client.MongoClient instance
     */
    @Bean
    public MongoClientFactoryBean mongo() {
        MongoClientFactoryBean mongo = new MongoClientFactoryBean();
        mongo.setHost("localhost");
        return mongo;
    }

    @Override
    protected String getDatabaseName() {
        return "application_db";
    }
}
