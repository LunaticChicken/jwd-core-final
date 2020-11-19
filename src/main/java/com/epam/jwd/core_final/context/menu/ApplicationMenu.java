package com.epam.jwd.core_final.context.menu;

import com.epam.jwd.core_final.Main;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.MissionNotCreatedException;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import com.epam.jwd.core_final.util.PropertyReaderUtil;
import com.epam.jwd.core_final.util.ValidInputUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;


// todo replace Object with your own types
public final class ApplicationMenu extends Menu {
    private static ApplicationMenu instance;

    private ApplicationMenu() {
    }

    public static ApplicationMenu getInstance() {
        if (instance == null) {
            instance = new ApplicationMenu();
        }
        return instance;
    }

    public void printAvailableOptions() {
        System.out.println("1 - Show info about entities\n"
                + "2 - Set filters\n"
                + "3 - Create mission\n"
                + "4 - Update last mission\n"
                + "0 - Exit program");
    }

    public void handleUserInput() throws MissionNotCreatedException {
        Main.logger.info("Main menu was opened");
        externalLoop:
        while (true) {
            int option = ValidInputUtil.getValidIntNumber(scanner);
            switch (option) {
                case 1:
                    InfoMenu.getInstance().printAvailableOptions();
                    InfoMenu.getInstance().handleUserInput();
                    printAvailableOptions();
                    break;
                case 2:
                    FiltersMenu.getInstance().printAvailableOptions();
                    FiltersMenu.getInstance().handleUserInput();
                    printAvailableOptions();
                    break;
                case 3:
                    System.out.println("Enter mission name:");
                    String missionName = scanner.next();
                    System.out.println("Enter start date:");
                    String startDate = getDateTime();
                    System.out.println("Enter end date:");
                    String endDate = getDateTime();
                    System.out.println("Enter mission distance:");
                    Long distance = ValidInputUtil.getValidLongNumber(scanner);
                    Mission newMission = createMission(missionName, startDate, endDate, distance);
                    NassaContext.getInstance().retrieveBaseEntityList(Mission.class).add(newMission);
                    outputMissionToJSON(newMission);
                    printAvailableOptions();
                    break;
                case 4:
                    System.out.println("Enter new mission parameters (name;startDate;endDate;distance)");
                    scanner.nextLine();
                    String[] missionParameters = scanner.nextLine().split(";");
                    List<Mission> missionList = (List<Mission>) NassaContext.getInstance()
                            .retrieveBaseEntityList(Mission.class);
                    MissionServiceImpl.getInstance().updateSpaceshipDetails(missionList.get(missionList.size() - 1),
                            missionParameters);
                    printAvailableOptions();
                    break;
                case 0:
                    scanner.close();
                    break externalLoop;
                default:
                    Main.logger.warn("Unknown command was entered");
                    System.out.println("Unknown command. Please, choose one of these options:");
                    printAvailableOptions();
                    break;
            }
        }
    }

    private static String getDateTime() {
        String startDateTime;
        while (true) {
            String startDate = scanner.next();
            String startTime = scanner.next();
            startDateTime = startDate + " " + startTime;
            if (ValidInputUtil.isInputEqualsDateFormat(startDateTime)) break;
            else {
                Main.logger.error("Not correct date format was entered");
                System.out.println("Not correct date format! Try again:");
            }
        }
        return startDateTime;
    }

    private static void outputMissionToJSON(Mission newMission) {
        ObjectMapper mapper = new ObjectMapper();
        ApplicationProperties properties = PropertyReaderUtil.loadProperties();
        File file = new File(properties.getOutputRootDir() + properties.getMissionsFileName() + ".json");
        try {
            mapper.writeValue(file, newMission);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Mission createMission(String name, String startDate, String endDate, Long distance)
            throws MissionNotCreatedException {
        Mission newMission = MissionServiceImpl.getInstance().createMission(name, startDate, endDate, distance);
        if (NassaContext.getInstance().retrieveBaseEntityList(Mission.class)
                .stream()
                .map(BaseEntity::getName)
                .anyMatch(missionName -> missionName.equals(newMission.getName()))) {
            throw new MissionNotCreatedException("Mission with this name was already created");
        }
        if (!SpaceshipServiceImpl.getInstance().assignSpaceshipOnMission(
                NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class),
                newMission)) {
            Main.logger.error("No available spaceships to the mission");
            throw new MissionNotCreatedException("No available spaceships to the mission");
        } else {
            newMission.setMissionResult(MissionResult.IN_PROGRESS);
            System.out.println("Mission created!");
            Main.logger.info("Mission was started");
            System.out.println("Mission was started");
        }
        CrewServiceImpl.getInstance().assignCrewMembersOnMission(
                NassaContext.getInstance().retrieveBaseEntityList(CrewMember.class), newMission);
        double missionResult = Math.random();
        if (missionResult < 0.1) {
            newMission.setMissionResult(MissionResult.FAILED);
            Main.logger.warn("Mission was failed");
            System.out.println("Oh no, our mission was failed."
                    + "Nobody could have predicted this ( except Math.random :) )");
        } else {
            newMission.setMissionResult(MissionResult.COMPLETED);
            Main.logger.warn("Mission was launched and completed successfully");
            System.out.println("Mission was launched and completed successfully!");
        }
        return newMission;
    }
}
