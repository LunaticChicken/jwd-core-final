package com.epam.jwd.core_final.service;

import com.epam.jwd.core_final.criteria.MissionCriteria;
import com.epam.jwd.core_final.domain.Mission;

import java.util.Collection;
import java.util.Optional;

public interface MissionService {

    Collection<Mission> findAllMissions();

    Collection<Mission> findAllMissionsByCriteria(MissionCriteria criteria);

    Optional<Mission> findMissionByCriteria(MissionCriteria criteria);

    Mission updateSpaceshipDetails(Mission mission);

    Mission createMission(String name, String sStartDate, String sEndDate, Long distance);
}
