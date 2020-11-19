package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.CrewMember} fields
 */
public class CrewMemberCriteria extends Criteria {

    private Role role;
    private Rank rank;
    private Boolean isReadyForNextMission;
    private static CrewMemberCriteria instance;

    private CrewMemberCriteria() {
    }

    public static CrewMemberCriteria getInstance() {
        if (instance == null) {
            instance = new CrewMemberCriteria();
        }
        return instance;
    }

    public static void deleteCriteria() {
        instance = new CrewMemberCriteria();
    }

    public CrewMemberCriteria role(Role role) {
        this.role = role;
        return this;
    }

    public CrewMemberCriteria rank(Rank rank) {
        this.rank = rank;
        return this;
    }

    public CrewMemberCriteria isReadyForNextMission(Boolean isReadyForNextMission) {
        this.isReadyForNextMission = isReadyForNextMission;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public Rank getRank() {
        return rank;
    }

    public Boolean getReadyForNextMission() {
        return isReadyForNextMission;
    }


}
