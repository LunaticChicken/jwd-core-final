package com.epam.jwd.core_final.domain;

import java.util.Map;
import java.util.Objects;

/**
 * crew {@link java.util.Map<Role, Short>}
 * flightDistance {@link Long} - total available flight distance
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class Spaceship extends AbstractBaseEntity {
    //todo
    private final Long flightDistance;
    private final Map<Role, Short> crew;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spaceship spaceship = (Spaceship) o;
        return flightDistance.equals(spaceship.flightDistance) &&
                crew.equals(spaceship.crew) &&
                isReadyForNextMission.equals(spaceship.isReadyForNextMission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightDistance, crew, isReadyForNextMission);
    }
}
