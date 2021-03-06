package com.epam.jwd.core_final.service;

import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.Mission;
import com.epam.jwd.core_final.domain.Spaceship;

import java.util.Collection;
import java.util.Optional;

/**
 * All its implementations should be a singleton
 * You have to use streamAPI for filtering, mapping, collecting, iterating
 */
public interface SpaceshipService {

    Collection<Spaceship> findAllSpaceships();

    Collection<Spaceship> findAllSpaceshipsByCriteria(Collection<Spaceship> spaceships,
                                                      SpaceshipCriteria criteria);

    Optional<Spaceship> findSpaceshipByCriteria(Collection<Spaceship> spaceships,
                                                SpaceshipCriteria criteria);

    // todo create custom exception for case, when spaceship is not able to be assigned
    boolean assignSpaceshipOnMission(Collection<Spaceship> spaceships, Mission mission) throws RuntimeException;

    // todo create custom exception for case, when crewMember is not able to be created (for example - duplicate.
    // spaceship unique criteria - only name!
    Spaceship createSpaceship(String spaceship) throws RuntimeException;
}
