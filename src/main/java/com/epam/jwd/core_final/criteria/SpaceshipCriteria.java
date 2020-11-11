package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.Spaceship;

/**
 * Should be a builder for {@link Spaceship} fields
 */
public class SpaceshipCriteria extends Criteria {

    private Long flightDistance;
    private Boolean isReadyForNextMission;
    private static SpaceshipCriteria instance;

    private SpaceshipCriteria() {
    }

    public static SpaceshipCriteria getInstance() {
        if (instance == null) {
            instance = new SpaceshipCriteria();
        }
        return instance;
    }

    public static void deleteCriteria() {
        instance = new SpaceshipCriteria();
    }

    public Criteria flightDistance(Long flightDistance) {
        this.flightDistance = flightDistance;
        return this;
    }

    public Criteria isReadyForNextMission(Boolean isReadyForNextMission) {
        this.isReadyForNextMission = isReadyForNextMission;
        return this;
    }

    public Long getFlightDistance() {
        return flightDistance;
    }

    public Boolean getReadyForNextMission() {
        return isReadyForNextMission;
    }
}
