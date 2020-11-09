package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Mission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Role;

import java.time.LocalDateTime;

/**
 * Should be a builder for {@link Mission} fields
 */
public class MissionCriteria extends Criteria {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long missionDistance;
    private MissionResult missionResult;
    private static MissionCriteria instance;

    private MissionCriteria() {}

    public static MissionCriteria getInstance() {
        if (instance == null) {
            instance = new MissionCriteria();
        }
        return instance;
    }

    public static void deleteCriteria() {
        instance = new MissionCriteria();
    }

    public Criteria startDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }
    public Criteria endDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }
    public Criteria missionDistance(Long missionDistance) {
        this.missionDistance = missionDistance;
        return this;
    }
    public Criteria missionResult(MissionResult missionResult) {
        this.missionResult = missionResult;
        return this;
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
}
