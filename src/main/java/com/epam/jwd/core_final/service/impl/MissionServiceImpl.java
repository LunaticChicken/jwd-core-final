package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.MissionCriteria;
import com.epam.jwd.core_final.domain.Mission;
import com.epam.jwd.core_final.factory.impl.MissionFactory;
import com.epam.jwd.core_final.service.MissionService;
import com.epam.jwd.core_final.util.TypeConversionUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class MissionServiceImpl implements MissionService {

    private static MissionServiceImpl instance;

    private MissionServiceImpl() {
    }

    public static MissionServiceImpl getInstance() {
        if (instance == null) {
            instance = new MissionServiceImpl();
        }
        return instance;
    }

    @Override
    public Collection<Mission> findAllMissions() {
        return NassaContext.getInstance().retrieveBaseEntityList(Mission.class);
    }

    @Override
    public Collection<Mission> findAllMissionsByCriteria(MissionCriteria criteria) {
        Collection<Mission> filteredMissions = new ArrayList<>();
        Collection<Mission> missions = NassaContext.getInstance().retrieveBaseEntityList(Mission.class);
        for (Mission mission : missions) {
            if (criteria.getId() != null && !mission.getId().equals(criteria.getId())) continue;
            if (criteria.getName() != null && !mission.getName().equals(criteria.getName())) continue;
            if (criteria.getMissionDistance() != null && !mission.getMissionDistance().equals(criteria.getMissionDistance()))
                continue;
            if (criteria.getStartDate() != null && !mission.getStartDate().equals(criteria.getStartDate())) continue;
            if (criteria.getEndDate() != null && !mission.getEndDate().equals(criteria.getEndDate())) continue;
            if (criteria.getMissionResult() != null && !mission.getMissionResult().equals(criteria.getMissionResult()))
                continue;
            filteredMissions.add(mission);
        }
        return filteredMissions;
    }

    @Override
    public Optional<Mission> findMissionByCriteria(MissionCriteria criteria) {
        Collection<Mission> missions = NassaContext.getInstance().retrieveBaseEntityList(Mission.class);
        for (Mission mission : missions) {
            if (criteria.getId() != null && !mission.getId().equals(criteria.getId())) continue;
            if (criteria.getName() != null && !mission.getName().equals(criteria.getName())) continue;
            if (criteria.getMissionDistance() != null && !mission.getMissionDistance().equals(criteria.getMissionDistance()))
                continue;
            if (criteria.getStartDate() != null && !mission.getStartDate().equals(criteria.getStartDate())) continue;
            if (criteria.getEndDate() != null && !mission.getEndDate().equals(criteria.getEndDate())) continue;
            if (criteria.getMissionResult() != null && !mission.getMissionResult().equals(criteria.getMissionResult()))
                continue;
            return Optional.of(mission);
        }
        return Optional.empty();
    }

    @Override
    public Mission updateSpaceshipDetails(Mission mission) {
        return null;
    }

    @Override
    public Mission createMission(String name, String sStartDate, String sEndDate, Long distance) {
        LocalDateTime startDate = TypeConversionUtil.stringToLocalDateTime(sStartDate);
        LocalDateTime endDate = TypeConversionUtil.stringToLocalDateTime(sEndDate);
        return MissionFactory.getInstance().create(name, startDate, endDate, distance);
    }
}
