package com.example.learnmongo.annotations;

import com.example.learnmongo.service.EventProcessorRegistry;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EventAnnotationProcessor {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private EventProcessorRegistry eventProcessorRegistry;

    @PostConstruct
    public void init() {
        Map<String, Object> beansWithAnnotationEventProcessorService = applicationContext.getBeansWithAnnotation(EventProcessorService.class);
        beansWithAnnotationEventProcessorService.forEach(this::registerEventProcessors);
    }

    private void registerEventProcessors(String key, Object value) {

        String[] eventIds = AnnotationUtils.findAnnotation(value.getClass(), EventProcessorService.class).eventIds();
        for (String eventId : eventIds) {
            eventProcessorRegistry.registerEventProcessor(eventId, value.getClass());
        }
    }
}
