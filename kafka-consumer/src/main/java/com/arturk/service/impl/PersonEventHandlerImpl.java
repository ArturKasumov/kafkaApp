package com.arturk.service.impl;

import com.arturk.entity.Person;
import com.arturk.service.ConsumerEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonEventHandlerImpl implements ConsumerEventHandler {
    private static final Logger log = LoggerFactory.getLogger(PersonEventHandlerImpl.class);
    @Override
    public void process(Object o) {
        log.info("Starting processing of person: {}", (Person) o);
    }
}
