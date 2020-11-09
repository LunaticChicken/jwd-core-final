package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.util.TypeConversionUtil;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class CrewServiceImpl implements CrewService {

    private static CrewServiceImpl instance;

    private CrewServiceImpl() {}

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
    public Collection<CrewMember> findAllCrewMembersByCriteria(CrewMemberCriteria criteria){
        Collection<CrewMember> filteredCrew = new ArrayList<>();
        Collection<CrewMember> crew = NassaContext.getInstance().retrieveBaseEntityList(CrewMember.class);
        for (CrewMember crewMember : crew) {
            if (criteria.getId() != null && !crewMember.getId().equals(criteria.getId())) continue;
            if (criteria.getName() != null && !crewMember.getName().equals(criteria.getName())) continue;
            if (criteria.getRank() != null && !crewMember.getRank().equals(criteria.getRank())) continue;
            if (criteria.getRole() != null && !crewMember.getRole().equals(criteria.getRole())) continue;
            if (criteria.getReadyForNextMission() != null
                    && crewMember.getReadyForNextMission().equals(criteria.getReadyForNextMission())) continue;
            filteredCrew.add(crewMember);
        }
        return filteredCrew;
    }

    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(CrewMemberCriteria criteria) {
        Collection<CrewMember> crew = NassaContext.getInstance().retrieveBaseEntityList(CrewMember.class);
        for (CrewMember crewMember : crew) {
            if (criteria.getId() != null && !crewMember.getId().equals(criteria.getId())) continue;
            if (criteria.getName() != null && !crewMember.getName().equals(criteria.getName())) continue;
            if (criteria.getRank() != null && !crewMember.getRank().equals(criteria.getRank())) continue;
            if (criteria.getRole() != null && !crewMember.getRole().equals(criteria.getRole())) continue;
            if (criteria.getReadyForNextMission() != null
                    && crewMember.getReadyForNextMission().equals(criteria.getReadyForNextMission())) continue;
            return Optional.of(crewMember);
        }
        return Optional.empty();
    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember crewMember) {
        return null;
    }

    @Override
    public void assignCrewMemberOnMission(CrewMember crewMember) throws RuntimeException {

    }

    @Override
    public CrewMember createCrewMember(String crewMember) throws RuntimeException {
        String[] crewMemberParameters = crewMember.split(",");
        Role role = TypeConversionUtil.stringToRole(crewMemberParameters[0]);
        String name = crewMemberParameters[1];
        Rank rank = TypeConversionUtil.stringToRank(crewMemberParameters[2]);
        return CrewMemberFactory.getInstance().create(role, name, rank);
    }
}
