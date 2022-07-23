package com.arturk.service;

import com.arturk.entity.Alien;
import com.arturk.service.impl.AlienEventHandlerImpl;
import com.arturk.service.impl.PersonEventHandlerImpl;

public interface ConsumerEventHandler {

    <T> void process(T o);

    static ConsumerEventHandler getHandler(Class<?> aClass) {
        if (aClass.equals(Alien.class)) {
            return new AlienEventHandlerImpl();
        } else {
            return new PersonEventHandlerImpl();
        }
    }
}
