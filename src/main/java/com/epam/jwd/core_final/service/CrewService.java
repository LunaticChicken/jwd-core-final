package com.epam.jwd.core_final.service;

import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Mission;

import java.util.Collection;
import java.util.Optional;

/**
 * All its implementations should be a singleton
 * You have to use streamAPI for filtering, mapping, collecting, iterating
 */
public interface CrewService {

    Collection<CrewMember> findAllCrewMembers();

    Collection<CrewMember> findAllCrewMembersByCriteria(CrewMemberCriteria criteria);

    Optional<CrewMember> findCrewMemberByCriteria(CrewMemberCriteria criteria);

    CrewMember updateCrewMemberDetails(CrewMember crewMember);

    // todo create custom exception for case, when crewMember is not able to be assigned
    boolean assignCrewMembersOnMission(Mission mission) throws RuntimeException;

    // todo create custom exception for case, when crewMember is not able to be created (for example - duplicate.
    // crewmember unique criteria - only name!
    CrewMember createCrewMember(String crewMember) throws RuntimeException;
}