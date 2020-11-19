package com.epam.jwd.core_final.service;

import com.epam.jwd.core_final.criteria.MissionCriteria;
import com.epam.jwd.core_final.domain.Mission;

import java.util.Collection;
import java.util.Optional;

public interface MissionService {

    Collection<Mission> findAllMissions();

    Collection<Mission> findAllMissionsByCriteria(Collection<Mission> missions, MissionCriteria criteria);

    Optional<Mission> findMissionByCriteria(Collection<Mission> missions, MissionCriteria criteria);

    Mission updateSpaceshipDetails(Mission mission, String[] missionParameters);

    Mission createMission(String name, String sStartDate, String sEndDate, Long distance);
}
