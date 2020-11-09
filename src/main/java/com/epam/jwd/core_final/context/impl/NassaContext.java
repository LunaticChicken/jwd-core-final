package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.MissionCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import com.epam.jwd.core_final.strategy.CrewReadingStrategy;
import com.epam.jwd.core_final.strategy.SpaceshipReadingStrategy;
import com.epam.jwd.core_final.util.PropertyReaderUtil;

import java.io.File;
import java.util.*;

// todo
public class NassaContext implements ApplicationContext {

    ApplicationProperties properties = PropertyReaderUtil.loadProperties();

    private Criteria crewCriteria = CrewMemberCriteria.getInstance();
    private Criteria spaceshipCriteria = SpaceshipCriteria.getInstance();
    private Criteria missionCriteria = MissionCriteria.getInstance();
    // no getters/setters for them
    private static final Collection<CrewMember> crewMembers = new ArrayList<>();
    private static final Collection<Spaceship> spaceships = new ArrayList<>();
    private static final Collection<Mission> missions = new ArrayList<>();

    private static NassaContext instance;

    private NassaContext() {}

    public static NassaContext getInstance() {
        if (instance == null) {
            instance = new NassaContext();
        }
        return instance;
    }

    @Override
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
        if (tClass.equals(CrewMember.class)) {
            return (Collection<T>) crewMembers;
        }
        if (tClass.equals(Spaceship.class)) {
            return (Collection<T>) spaceships;
        }
        return (Collection<T>) missions;
    }

    public Criteria getCrewCriteria() {
        return crewCriteria;
    }

    public void setCrewCriteria(Criteria crewCriteria) {
        this.crewCriteria = crewCriteria;
    }

    public Criteria getSpaceshipCriteria() {
        return spaceshipCriteria;
    }

    public void setSpaceshipCriteria(Criteria spaceshipCriteria) {
        this.spaceshipCriteria = spaceshipCriteria;
    }

    public Criteria getMissionCriteria() {
        return missionCriteria;
    }

    public void setMissionCriteria(Criteria missionCriteria) {
        this.missionCriteria = missionCriteria;
    }

    /**
     * You have to read input files, populate collections
     *
     * @throws InvalidStateException
     */
    @Override
    public void init() throws InvalidStateException {
        File inputRootDir = new File(properties.getInputRootDir());
        File[] listFiles = inputRootDir.listFiles();
        for (File file : listFiles) {
            if (file.getName().equals(properties.getCrewFileName())) {
                String crewInFile = CrewReadingStrategy.getInstance().read(file);
                populateCrewCollection(crewInFile);
            }
            if (file.getName().equals(properties.getSpaceshipsFileName())) {
                String spaceshipsInFile = SpaceshipReadingStrategy.getInstance().read(file);
                populateSpaceshipsCollection(spaceshipsInFile);
            }
        }
    }

    private static void populateCrewCollection(String crewInFile) {
        for (String crewMember : crewInFile.split(";")) {
            crewMembers.add(CrewServiceImpl.getInstance().createCrewMember(crewMember));
        }
    }

    private static void populateSpaceshipsCollection(String spaceshipsInFile) {
        for (String spaceship : spaceshipsInFile.split("\n")) {
            spaceships.add(SpaceshipServiceImpl.getInstance().createSpaceship(spaceship));
        }
    }
}
