package com.epam.jwd.core_final.context.menu;

import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.MissionCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.util.ValidInputUtil;

public final class FiltersMenu extends Menu {

    private static FiltersMenu instance;

    private FiltersMenu() {
    }

    public static FiltersMenu getInstance() {
        if (instance == null) {
            instance = new FiltersMenu();
        }
        return instance;
    }

    @Override
    public void printAvailableOptions() {
        System.out.println("1 - Change crew filters\n"
                + "2 - Change spaceship filters\n"
                + "3 - Change mission filters\n"
                + "4 - Delete all filters\n"
                + "0 - Back");
    }

    @Override
    public void handleUserInput() {
        externalLoop:
        while (true) {
            int option = ValidInputUtil.getValidIntNumber(scanner);
            switch (option) {
                case 1:
                    CrewFiltersMenu.getInstance().printAvailableOptions();
                    CrewFiltersMenu.getInstance().handleUserInput();
                    printAvailableOptions();
                    break;
                case 2:
                    SpaceshipFiltersMenu.getInstance().printAvailableOptions();
                    SpaceshipFiltersMenu.getInstance().handleUserInput();
                    printAvailableOptions();
                    break;
                case 3:
                    MissionFiltersMenu.getInstance().printAvailableOptions();
                    MissionFiltersMenu.getInstance().handleUserInput();
                    printAvailableOptions();
                case 4:
                    deleteFilters();
                    System.out.println("Filters deleted.");
                    break;
                case 0:
                    break externalLoop;
                default:
                    System.out.println("Unknown command. Please, choose one of these options:");
                    printAvailableOptions();
                    break;
            }
        }
    }

    private void deleteFilters() {
        CrewMemberCriteria.deleteCriteria();
        SpaceshipCriteria.deleteCriteria();
        MissionCriteria.deleteCriteria();
    }
}
