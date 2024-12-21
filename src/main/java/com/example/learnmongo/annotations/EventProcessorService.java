package com.example.learnmongo.annotations;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EventProcessorService {

    /**
     * Parameter eventIds is used to register Bean annotated with @EventProcessor.
     * @return eventId
     */
    String[] eventIds();
}
