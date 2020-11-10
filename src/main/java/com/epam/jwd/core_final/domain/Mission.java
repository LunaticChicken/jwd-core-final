package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.context.impl.NassaContext;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private Spaceship assignedSpaceship;
    private List<CrewMember> assignedCrew = new ArrayList<>();
    private MissionResult missionResult;

    public Mission(String name, LocalDateTime startDate, LocalDateTime endDate, Long missionDistance) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
        this.missionDistance = missionDistance;
        for (Spaceship spaceship : NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class)) {
            if (spaceship.getReadyForNextMission() && spaceship.getFlightDistance() >= missionDistance) {
                this.assignedSpaceship = spaceship;
                spaceship.setReadyForNextMission(false);
                break;
            }
        }
        externalLoop:
        for (CrewMember crewMember : NassaContext.getInstance().retrieveBaseEntityList(CrewMember.class)) {
            for (Map.Entry<Role, Short> mapEntry : assignedSpaceship.getCrew().entrySet()) {
                if (crewMember.getReadyForNextMission() && crewMember.getRole() == mapEntry.getKey() &&
                        assignedCrew.stream().filter(c -> c.getRole() == mapEntry.getKey()).count() <= mapEntry.getValue()) {
                    assignedCrew.add(crewMember);
                    crewMember.setReadyForNextMission(false);
                    break externalLoop;
                }
            }
        }
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

    public void setAssignedCrew(List<CrewMember> assignedCrew) {
        this.assignedCrew = assignedCrew;
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
}
