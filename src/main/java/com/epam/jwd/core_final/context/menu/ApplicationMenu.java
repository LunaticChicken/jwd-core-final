package com.epam.jwd.core_final.context.menu;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.domain.Mission;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.util.ValidInputUtil;


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

    public void handleUserInput() {
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
                    String missionName = scanner.nextLine();
                    System.out.println("Enter start date:");
                    String startDate = getDateTime();
                    System.out.println("Enter end date:");
                    String endDate = getDateTime();
                    System.out.println("Enter mission distance:");
                    Long distance = ValidInputUtil.getValidLongNumber(scanner);
                    NassaContext.getInstance().retrieveBaseEntityList(Mission.class).add(
                            MissionServiceImpl.getInstance().createMission(missionName, startDate, endDate, distance));
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
            startDateTime = scanner.nextLine();
            if (ValidInputUtil.isInputEqualsDateFormat(startDateTime)) break;
            else System.out.println("Not correct date format! Try again:");
        }
        return startDateTime;
    }
}
