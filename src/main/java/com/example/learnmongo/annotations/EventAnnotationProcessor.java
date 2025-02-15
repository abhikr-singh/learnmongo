package com.example.learnmongo.annotations;

import com.example.learnmongo.service.EventProcessorRegistry;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class EventAnnotationProcessor {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private EventProcessorRegistry eventProcessorRegistry;

    @PostConstruct
    public void init() {
        Map<String, Object> beansWithAnnotationEventProcessorService = applicationContext
                .getBeansWithAnnotation(EventProcessorService.class);
        beansWithAnnotationEventProcessorService.forEach(this::registerEventProcessors);
    }

    private void registerEventProcessors(String key, Object value) {

        EventProcessorService annotation = AnnotationUtils.findAnnotation(value.getClass(),
                EventProcessorService.class);
        if (Objects.nonNull(annotation)) {
            String[] eventIds = annotation.eventIds();
            for (String eventId : eventIds) {
                log.info("Registering event processor {} for event {}", value.getClass().getCanonicalName(), eventId);
                eventProcessorRegistry.registerEventProcessor(eventId, value.getClass());
            }
        }
    }
}
