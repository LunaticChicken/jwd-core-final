package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.util.TypeConversionUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CrewServiceImpl implements CrewService {

    private static CrewServiceImpl instance;

    private CrewServiceImpl() {
    }

    public static CrewServiceImpl getInstance() {
        if (instance == null) {
            instance = new CrewServiceImpl();
        }
        return instance;
    }

    @Override
    public Collection<CrewMember> findAllCrewMembers() {
        return NassaContext.getInstance().retrieveBaseEntityList(CrewMember.class);
    }

    @Override
    public Collection<CrewMember> findAllCrewMembersByCriteria(Collection<CrewMember> crew,
                                                               CrewMemberCriteria criteria) {
        return getFilteredCrewStream(crew, criteria)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(Collection<CrewMember> crew,
                                                         CrewMemberCriteria criteria) {
        return getFilteredCrewStream(crew, criteria)
                .findFirst();
    }

    @Override
    public boolean assignCrewMembersOnMission(Collection<CrewMember> crewMembers, Mission mission) {
        externalLoop:
        for (CrewMember crewMember : crewMembers) {
            for (Map.Entry<Role, Short> mapEntry : mission
                    .getAssignedSpaceship()
                    .getCrew()
                    .entrySet()) {
                if (mission.getAssignedCrew().stream()
                        .filter(c -> c.getRole() == mapEntry.getKey()).count() >= mapEntry.getValue()) {
                    break externalLoop;
                }
                if (crewMember.getReadyForNextMission() && crewMember.getRole() == mapEntry.getKey()) {
                    mission.getAssignedCrew().add(crewMember);
                    crewMember.setReadyForNextMission(false);
                }
            }
        }
        return mission.getAssignedCrew().size() != 0;
    }

    @Override
    public CrewMember createCrewMember(String crewMember) throws RuntimeException {
        String[] crewMemberParameters = crewMember.split(",");
        Role role = TypeConversionUtil.stringToRole(crewMemberParameters[0]);
        String name = crewMemberParameters[1];
        Rank rank = TypeConversionUtil.stringToRank(crewMemberParameters[2]);
        return CrewMemberFactory.getInstance().create(role, name, rank);
    }

    private Stream<CrewMember> getFilteredCrewStream(Collection<CrewMember> crew, CrewMemberCriteria criteria) {
        return crew.stream()
                .filter(crewMember -> criteria.getId() == null || crewMember.getId().equals(criteria.getId()))
                .filter(crewMember -> criteria.getName() == null || crewMember.getName().equals(criteria.getName()))
                .filter(crewMember -> criteria.getRank() == null || crewMember.getRank().equals(criteria.getRank()))
                .filter(crewMember -> criteria.getRole() == null || crewMember.getRole().equals(criteria.getRole()));
    }
}
