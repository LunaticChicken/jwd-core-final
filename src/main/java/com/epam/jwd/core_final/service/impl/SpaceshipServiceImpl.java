package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.util.TypeConversionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SpaceshipServiceImpl implements SpaceshipService {

    private static SpaceshipServiceImpl instance;

    public static SpaceshipServiceImpl getInstance() {
        if (instance == null) {
            instance = new SpaceshipServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Spaceship> findAllSpaceships() {
        return null;
    }

    @Override
    public List<Spaceship> findAllSpaceshipsByCriteria(Criteria<? extends Spaceship> criteria) {
        return null;
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(Criteria<? extends Spaceship> criteria) {
        return Optional.empty();
    }

    @Override
    public Spaceship updateSpaceshipDetails(Spaceship spaceship) {
        return null;
    }

    @Override
    public void assignSpaceshipOnMission(Spaceship crewMember) throws RuntimeException {

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
