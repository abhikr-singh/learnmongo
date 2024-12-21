package com.example.learnmongo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class EventProcessorRegistry {

    @Autowired
    private ApplicationContext applicationContext;

    private final Map<String, Class<?>> eventProcessors = new HashMap<>();

    public void registerEventProcessor(String eventType, Class<?> eventProcessorClass) {
        eventProcessors.put(eventType, eventProcessorClass);
        log.info("Registered event processor for event type: {} and processor: {}",
                eventType, eventProcessorClass.getCanonicalName());
    }

    public IEventProcessorService getEventProcessor(String eventType) {
        Class<?> eventProcessorClass = eventProcessors.get(eventType);
        if (eventProcessorClass == null) {
            throw new IllegalArgumentException("No event processor registered for event type: " + eventType);
        }
        try {
            return (IEventProcessorService) this.applicationContext.getBean(eventProcessors.get(eventType));
        } catch (Exception e) {
            throw new RuntimeException("Failed to create instance of event processor: " + eventProcessorClass.getCanonicalName(), e);
        }
    }


}