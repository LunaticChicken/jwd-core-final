package com.epam.jwd.core_final.domain;

/**
 * Expected fields:
 * <p>
 * role {@link Role} - member role
 * rank {@link Rank} - member rank
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class CrewMember extends AbstractBaseEntity {
    // todo
    private Role role;
    private Rank rank;
    private Boolean isReadyForNextMission = true;

    public CrewMember(Role role, String name, Rank rank) {
        super(name);
        this.role = role;
        this.rank = rank;
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

    public void setReadyForNextMission(Boolean readyForNextMissions) {
        isReadyForNextMission = readyForNextMissions;
    }

    @Override
    public String toString() {
        return "CrewMember{" +
                "role=" + role +
                ", rank=" + rank +
                '}';
    }
}
