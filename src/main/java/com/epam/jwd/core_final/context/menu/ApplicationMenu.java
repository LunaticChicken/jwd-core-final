package com.epam.jwd.core_final.context.menu;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.Mission;
import com.epam.jwd.core_final.exception.MissionNotCreatedException;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import com.epam.jwd.core_final.util.PropertyReaderUtil;
import com.epam.jwd.core_final.util.ValidInputUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;


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
                + "4 - Manage missions\n"
                + "0 - Exit program");
    }

    public void handleUserInput() throws MissionNotCreatedException {
        logger.info("Main menu was opened");
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
                    System.out.println("Mission created!");
                    outputMissionToJSON(newMission);
                    printAvailableOptions();
                    break;
                case 0:
                    scanner.close();
                    break externalLoop;
                default:
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
            else System.out.println("Not correct date format! Try again:");
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
        if (!SpaceshipServiceImpl.getInstance().assignSpaceshipOnMission(newMission)) {
            throw new MissionNotCreatedException("No available spaceships for this mission");
        }
        CrewServiceImpl.getInstance().assignCrewMembersOnMission(newMission);
        return newMission;
    }
}
