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
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public Collection<Mission> findAllMissionsByCriteria(Collection<Mission> missions, MissionCriteria criteria) {
        return getFilteredMissionStream(missions, criteria)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Optional<Mission> findMissionByCriteria(Collection<Mission> missions, MissionCriteria criteria) {
        return getFilteredMissionStream(missions, criteria)
                .findFirst();
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

    private Stream<Mission> getFilteredMissionStream(Collection<Mission> missions, MissionCriteria criteria) {
        return missions.stream()
                .filter(mission -> criteria.getId() == null
                        || mission.getId().equals(criteria.getId()))
                .filter(mission -> criteria.getName() == null
                        || mission.getName().equals(criteria.getName()))
                .filter(mission -> criteria.getMissionDistance() == null
                        || (mission.getMissionDistance() >= criteria.getMissionDistance() - 50000
                        && mission.getMissionDistance() <= criteria.getMissionDistance() + 50000))
                .filter(mission -> criteria.getStartDate() == null
                        || mission.getStartDate().equals(criteria.getStartDate()))
                .filter(mission -> criteria.getEndDate() == null
                        || mission.getEndDate().equals(criteria.getEndDate()))
                .filter(mission -> criteria.getMissionResult() == null
                        || mission.getMissionResult().equals(criteria.getMissionResult()));
    }
}
