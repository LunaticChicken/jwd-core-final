package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;

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
    private Boolean isReadyForNextMission = true;

    public Spaceship(String name, Long flightDistance, Map<Role, Short> crew) {
        super(name);
        this.flightDistance = flightDistance;
        this.crew = crew;
    }

    public Long getFlightDistance() {
        return flightDistance;
    }

    public Map<Role, Short> getCrew() {
        return crew;
    }

    public Boolean getReadyForNextMission() {
        return isReadyForNextMission;
    }

    public void setReadyForNextMission(Boolean readyForNextMissions) {
        isReadyForNextMission = readyForNextMissions;
    }

    @Override
    public String toString() {
        return "Spaceship{" +
                "flightDistance=" + flightDistance +
                ", crew=" + crew +
                '}';
    }
}
