package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.Mission;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.time.LocalDateTime;

public class MissionFactory implements EntityFactory<Mission> {

    private static MissionFactory instance;

    public static MissionFactory getInstance() {
        if (instance == null) {
            instance = new MissionFactory();
        }
        return instance;
    }

    @Override
    public Mission create(Object... args) {
        return new Mission((String) args[0], (LocalDateTime) args[1], (LocalDateTime) args[2], (Long) args[3]);
    }
}
