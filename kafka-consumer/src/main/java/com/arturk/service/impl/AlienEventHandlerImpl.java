package com.arturk.service.impl;

import com.arturk.entity.Alien;
import com.arturk.service.ConsumerEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlienEventHandlerImpl implements ConsumerEventHandler {
    private static final Logger log = LoggerFactory.getLogger(AlienEventHandlerImpl.class);

    @Override
    public <T> void process(T o) {
        log.info("Starting processing of alien: {}", (Alien) o);
    }
}
