package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import com.epam.jwd.core_final.strategy.CrewReadingStrategy;
import com.epam.jwd.core_final.strategy.SpaceshipReadingStrategy;
import com.epam.jwd.core_final.util.PropertyReaderUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// todo
public class NassaContext implements ApplicationContext {

    ApplicationProperties properties = PropertyReaderUtil.loadProperties();
    private static NassaContext instance;
    // no getters/setters for them
    private static Collection<CrewMember> crewMembers = new ArrayList<>();
    private static Collection<Spaceship> spaceships = new ArrayList<>();

    public static NassaContext getInstance() {
        if (instance == null) {
            instance = new NassaContext();
        }
        return instance;
    }

    @Override
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
        return null;
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
