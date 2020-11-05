package com.epam.jwd.core_final.domain;

import java.util.Map;

/**
 * crew {@link java.util.Map<Role, Short>}
 * flightDistance {@link Long} - total available flight distance
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class Spaceship extends AbstractBaseEntity {
    //todo
    private Long flightDistance;
    private Map<Role, Short> crew;
    private Boolean isReadyForNextMissions = true;

    public Spaceship(String name, Long flightDistance, Map<Role, Short> crew) {
        super(name);
        this.flightDistance = flightDistance;
        this.crew = crew;
    }
}
