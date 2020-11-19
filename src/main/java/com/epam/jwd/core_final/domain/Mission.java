package com.epam.jwd.core_final.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Expected fields:
 * <p>
 * missions name {@link String}
 * start date {@link java.time.LocalDate}
 * end date {@link java.time.LocalDate}
 * distance {@link Long} - missions distance
 * assignedSpaceShift {@link Spaceship} - not defined by default
 * assignedCrew {@link java.util.List<CrewMember>} - list of missions members based on ship capacity - not defined by default
 * missionResult {@link MissionResult}
 */
public class Mission extends AbstractBaseEntity {
    // todo
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long missionDistance;
    private List<CrewMember> assignedCrew = new ArrayList<>();
    private Spaceship assignedSpaceship;
    private MissionResult missionResult;

    public Mission(String name, LocalDateTime startDate, LocalDateTime endDate, Long missionDistance) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
        this.missionDistance = missionDistance;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Long getMissionDistance() {
        return missionDistance;
    }

    public MissionResult getMissionResult() {
        return missionResult;
    }

    public Spaceship getAssignedSpaceship() {
        return assignedSpaceship;
    }

    public void setAssignedSpaceship(Spaceship assignedSpaceship) {
        this.assignedSpaceship = assignedSpaceship;
    }

    public List<CrewMember> getAssignedCrew() {
        return assignedCrew;
    }

    public void setMissionResult(MissionResult missionResult) {
        this.missionResult = missionResult;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setMissionDistance(Long missionDistance) {
        this.missionDistance = missionDistance;
    }

    @Override
    public String toString() {
        return "Mission{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", missionDistance=" + missionDistance +
                ", assignedSpaceship=" + assignedSpaceship +
                ", assignedCrew=" + assignedCrew +
                ", missionResult=" + missionResult +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mission mission = (Mission) o;
        return startDate.equals(mission.startDate) &&
                endDate.equals(mission.endDate) &&
                missionDistance.equals(mission.missionDistance) &&
                Objects.equals(assignedSpaceship, mission.assignedSpaceship) &&
                Objects.equals(assignedCrew, mission.assignedCrew) &&
                missionResult == mission.missionResult;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, missionDistance, assignedSpaceship, assignedCrew, missionResult);
    }
}
