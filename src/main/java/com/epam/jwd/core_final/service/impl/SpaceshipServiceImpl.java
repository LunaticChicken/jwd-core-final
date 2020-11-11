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
    public Collection<Spaceship> findAllSpaceshipsByCriteria(SpaceshipCriteria criteria) {
        Collection<Spaceship> filteredSpaceships = new ArrayList<>();
        Collection<Spaceship> spaceships = NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class);
        for (Spaceship spaceship : spaceships) {
            if (criteria.getId() != null && !spaceship.getId().equals(criteria.getId())) continue;
            if (criteria.getName() != null && !spaceship.getName().equals(criteria.getName())) continue;
            if (criteria.getFlightDistance() != null && !spaceship.getFlightDistance().equals(criteria.getFlightDistance()))
                continue;
            if (criteria.getReadyForNextMission() != null && !spaceship.getReadyForNextMission().equals(criteria.getReadyForNextMission()))
                continue;
            filteredSpaceships.add(spaceship);
        }
        return filteredSpaceships;
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(SpaceshipCriteria criteria) {
        Collection<Spaceship> spaceships = NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class);
        for (Spaceship spaceship : spaceships) {
            if (criteria.getId() != null && !spaceship.getId().equals(criteria.getId())) continue;
            if (criteria.getName() != null && !spaceship.getName().equals(criteria.getName())) continue;
            if (criteria.getFlightDistance() != null && !spaceship.getFlightDistance().equals(criteria.getFlightDistance()))
                continue;
            if (criteria.getReadyForNextMission() != null && !spaceship.getReadyForNextMission().equals(criteria.getReadyForNextMission()))
                continue;
            return Optional.of(spaceship);
        }
        return Optional.empty();
    }

    @Override
    public Spaceship updateSpaceshipDetails(Spaceship spaceship) {
        return null;
    }

    @Override
    public boolean assignSpaceshipOnMission(Mission mission) throws RuntimeException {
        for (Spaceship spaceship : NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class)) {
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
