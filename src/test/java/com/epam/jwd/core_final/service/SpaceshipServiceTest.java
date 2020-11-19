package com.epam.jwd.core_final.service;

import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.Mission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SpaceshipServiceTest {

    private static final SpaceshipService service = SpaceshipServiceImpl.getInstance();

    private static final List<Spaceship> spaceships = Arrays.asList(
            service.createSpaceship("Challenger;201117;{1:5,2:9,3:3,4:3}"),
            service.createSpaceship("Intelligence;758717;{1:7,2:8,3:4,4:1}"),
            service.createSpaceship("Philadelphia;4870;{1:4,2:8,3:4,4:2}"),
            service.createSpaceship("Sparta;754471;{1:7,2:7,3:2,4:1}"),
            service.createSpaceship("Guardian;910740;{1:8,2:9,3:2,4:1}"),
            service.createSpaceship("CS Adder;472285;{1:9,2:7,3:2,4:2}")
    );

    @Before
    public void deleteCriteria() {
        SpaceshipCriteria.deleteCriteria();
    }

    @Test
    public void testCheckIfAllSpaceshipsMatchCriteria() {
        SpaceshipCriteria criteria = SpaceshipCriteria.getInstance()
                .flightDistance(750000L);
        List<Spaceship> requiredSpaceships = Arrays.asList(
                spaceships.get(1),
                spaceships.get(3)
        );
        Assert.assertEquals(SpaceshipServiceImpl.getInstance().findAllSpaceshipsByCriteria(spaceships, criteria),
                requiredSpaceships);
    }

    @Test
    public void testCheckIfSpaceshipMatchesCriteria() {
        SpaceshipCriteria criteria = SpaceshipCriteria.getInstance()
                .flightDistance(1000L);
        Optional<Spaceship> requiredSpaceship =
                Optional.of(spaceships.get(2));
        Assert.assertEquals(SpaceshipServiceImpl.getInstance().findSpaceshipByCriteria(spaceships, criteria),
                requiredSpaceship);
    }

    @Test
    public void testCheckIfSpaceshipWasAssignedOnMission() {
        Mission mission1 = MissionServiceImpl.getInstance().createMission
                ("", "2020-12-12 12:12:12","2020-12-12 12:12:12", 50000L);
        Mission mission2 = MissionServiceImpl.getInstance().createMission
                ("", "2020-12-12 12:12:12","2020-12-12 12:12:12", 999999999L);
        Assert.assertTrue(service.assignSpaceshipOnMission(spaceships, mission1));
        Assert.assertFalse(service.assignSpaceshipOnMission(spaceships, mission2));
    }
}
