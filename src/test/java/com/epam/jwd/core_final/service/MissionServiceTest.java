package com.epam.jwd.core_final.service;

import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.MissionCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Mission;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MissionServiceTest {
    private static final MissionService service = MissionServiceImpl.getInstance();

    private static final List<Mission> missions = Arrays.asList(
            service.createMission("a", "2020-12-12 12:12:12", "2020-12-12 12:12:12", 50000L),
            service.createMission("b", "2020-12-12 12:12:12", "2020-12-12 12:12:12", 20000L),
            service.createMission("c", "2020-12-12 12:12:12", "2020-12-12 12:12:12", 30000L),
            service.createMission("d", "2020-12-12 12:12:12", "2020-12-12 12:12:12", 10000L),
            service.createMission("f", "2020-12-12 12:12:12", "2020-12-12 12:12:12", 40000L)
    );

    @Before
    public void deleteCriteria() {
        MissionCriteria.deleteCriteria();
    }

    @Test
    public void testCheckIfAllMissionsMatchCriteria() {
        MissionCriteria criteria = MissionCriteria.getInstance()
                .missionDistance(80000L);
        List<Mission> requiredMissions = Arrays.asList(
                missions.get(0),
                missions.get(2),
                missions.get(4)
        );
        Assert.assertEquals(MissionServiceImpl.getInstance().findAllMissionsByCriteria(missions, criteria),
                requiredMissions);
    }

    @Test
    public void testCheckIfMissionMatchesCriteria() {
        MissionCriteria criteria = (MissionCriteria) MissionCriteria.getInstance()
                .name("a");
        Optional<Mission> requiredMission =
                Optional.of(missions.get(0));
        Assert.assertEquals(MissionServiceImpl.getInstance().findMissionByCriteria(missions, criteria),
                requiredMission);
    }
}
