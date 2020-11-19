package com.epam.jwd.core_final.service;

import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Mission;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CrewServiceTest {

    private static final CrewService service = CrewServiceImpl.getInstance();

    private static final List<CrewMember> crewMembers = Arrays.asList(
            service.createCrewMember("4,Matthew Sheehan,1"),
            service.createCrewMember("2,Sally Gonzales,4"),
            service.createCrewMember("1,Jordyn Henson,2"),
            service.createCrewMember("2,Britney Dunn,1"),
            service.createCrewMember("4,Shayan Mclellan,1")
    );

    @Before
    public void deleteCriteria() {
        CrewMemberCriteria.deleteCriteria();
    }

    @Test
    public void testCheckIfAllCrewMembersMatchCriteria() {
        CrewMemberCriteria criteria = CrewMemberCriteria.getInstance()
                .role(Role.COMMANDER)
                .rank(Rank.TRAINEE);
        List<CrewMember> requiredCrewMembers = Arrays.asList(
                crewMembers.get(0),
                crewMembers.get(4)
        );
        Assert.assertEquals(CrewServiceImpl.getInstance().findAllCrewMembersByCriteria(crewMembers, criteria),
                requiredCrewMembers);
    }

    @Test
    public void testCheckIfCrewMemberMatchesCriteria() {
        CrewMemberCriteria criteria = (CrewMemberCriteria) CrewMemberCriteria.getInstance()
                .name("Jordyn Henson");
        Optional<CrewMember> requiredCrewMember =
                Optional.of(crewMembers.get(2));
        Assert.assertEquals(CrewServiceImpl.getInstance().findCrewMemberByCriteria(crewMembers, criteria),
                requiredCrewMember);
    }

    @Test
    public void testCheckIfCrewMembersWereAssignedOnMission() {
        Mission mission = getMissionWithAssignedSpaceship();

        Assert.assertTrue(service.assignCrewMembersOnMission(crewMembers, mission));
    }

    private Mission getMissionWithAssignedSpaceship() {
        SpaceshipService spaceshipService = SpaceshipServiceImpl.getInstance();
        Mission mission = MissionServiceImpl.getInstance().createMission
                ("", "2020-12-12 12:12:12","2020-12-12 12:12:12", 500000L);
        spaceshipService.assignSpaceshipOnMission(Collections.singletonList(
                spaceshipService.createSpaceship("Intelligence;758717;{1:7,2:8,3:4,4:1}")), mission);
        return mission;
    }
}
