package com.example.learnmongo.service.impl;


import com.example.learnmongo.annotations.EventProcessorService;
import com.example.learnmongo.service.IEventProcessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@EventProcessorService(eventIds = {"simpleEvent"})
@Slf4j
public class SimpleEventProcessor implements IEventProcessorService {
    @Override
    public void processEvent(String eventId) {
        log.info("Processing event Id: {}", eventId);
    }
}
