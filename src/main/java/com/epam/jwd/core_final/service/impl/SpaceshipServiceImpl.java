package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.Mission;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.util.TypeConversionUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SpaceshipServiceImpl implements SpaceshipService {

    private static SpaceshipServiceImpl instance;

    private SpaceshipServiceImpl() {
    }

    public static SpaceshipServiceImpl getInstance() {
        if (instance == null) {
            instance = new SpaceshipServiceImpl();
        }
        return instance;
    }

    @Override
    public Collection<Spaceship> findAllSpaceships() {
        return NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class);
    }

    @Override
    public Collection<Spaceship> findAllSpaceshipsByCriteria(Collection<Spaceship> spaceships,
                                                             SpaceshipCriteria criteria) {
        return getFilteredSpaceshipStream(spaceships, criteria)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(Collection<Spaceship> spaceships,
                                                       SpaceshipCriteria criteria) {
        return getFilteredSpaceshipStream(spaceships, criteria)
                .findFirst();
    }

    @Override
    public boolean assignSpaceshipOnMission(Collection<Spaceship> spaceships, Mission mission) throws RuntimeException {
        for (Spaceship spaceship : spaceships) {
            if (spaceship.getReadyForNextMission() && spaceship.getFlightDistance() >= mission.getMissionDistance()) {
                mission.setAssignedSpaceship(spaceship);
                spaceship.setReadyForNextMission(false);
                break;
            }
        }
        return mission.getAssignedSpaceship() != null;
    }

    @Override
    public Spaceship createSpaceship(String spaceship) throws RuntimeException {
        String[] spaceshipParameters = spaceship.split(";");
        String name = spaceshipParameters[0];
        Long flightDistance = Long.parseLong(spaceshipParameters[1]);
        Map<Role, Short> crew = getCrew(spaceshipParameters[2]);
        return SpaceshipFactory.getInstance().create(name, flightDistance, crew);
    }

    private Stream<Spaceship> getFilteredSpaceshipStream(Collection<Spaceship> spaceships, SpaceshipCriteria criteria) {
        return spaceships.stream()
                .filter(spaceship -> criteria.getId() == null || spaceship.getId().equals(criteria.getId()))
                .filter(spaceship -> criteria.getName() == null || spaceship.getName().equals(criteria.getName()))
                .filter(spaceship -> criteria.getFlightDistance() == null
                        || (spaceship.getFlightDistance() >= criteria.getFlightDistance() - 50000
                        && spaceship.getFlightDistance() <= criteria.getFlightDistance() + 50000))
                .filter(spaceship -> criteria.getReadyForNextMission() == null
                        || spaceship.getReadyForNextMission().equals(criteria.getReadyForNextMission()));
    }

    private static Map<Role, Short> getCrew(String crew) {
        crew = crew.substring(1, crew.length() - 1);
        Map<Role, Short> mapCrew = new HashMap<>();
        for (String mapEntry : crew.split(",")) {
            Role role = TypeConversionUtil.stringToRole(mapEntry.split(":")[0]);
            Short numberOfMembersOnAppropriateRole = Short.parseShort(mapEntry.split(":")[1]);
            mapCrew.put(role, numberOfMembersOnAppropriateRole);
        }
        return mapCrew;
    }
}
