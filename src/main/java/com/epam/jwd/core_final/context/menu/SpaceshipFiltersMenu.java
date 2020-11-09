package com.epam.jwd.core_final.context.menu;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.util.ValidInputUtil;

public final class SpaceshipFiltersMenu extends Menu {

    private static SpaceshipFiltersMenu instance;

    private SpaceshipFiltersMenu() {
    }

    public static SpaceshipFiltersMenu getInstance() {
        if (instance == null) {
            instance = new SpaceshipFiltersMenu();
        }
        return instance;
    }

    @Override
    public void printAvailableOptions() {
        System.out.println("1 - Set id filter\n"
                + "2 - Set name filter\n"
                + "3 - Set flight distance\n"
                + "4 - Set ready filter\n"
                + "0 - Back");
    }

    @Override
    public void handleUserInput() {
        externalLoop:
        while (true) {
            int option = ValidInputUtil.getValidIntNumber(scanner);
            switch (option) {
                case 1:
                    System.out.print("Enter id: ");
                    Long id = ValidInputUtil.getValidLongNumber(scanner);
                    setIdFilter(id);
                    break;
                case 2:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    setNameFilter(name);
                    break;
                case 3:
                    System.out.print("Enter flight distance: ");
                    Long distance = ValidInputUtil.getValidLongNumber(scanner);
                    setDistanceFilter(distance);
                    break;
                case 4:
                    System.out.print("Enter ready status: ");
                    Boolean isReady = ValidInputUtil.getReadyStatus(scanner);
                    setReadyFilter(isReady);
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

    private void setIdFilter(Long id) {
        NassaContext.getInstance().setSpaceshipCriteria(SpaceshipCriteria.getInstance().id(id));
    }

    private void setNameFilter(String name) {
        NassaContext.getInstance().setSpaceshipCriteria(SpaceshipCriteria.getInstance().name(name));
    }

    private void setDistanceFilter(Long distance) {
        NassaContext.getInstance().setSpaceshipCriteria(SpaceshipCriteria.getInstance().flightDistance(distance));
    }

    private void setReadyFilter(Boolean isReady) {
        NassaContext.getInstance().setSpaceshipCriteria(SpaceshipCriteria.getInstance().isReadyForNextMission(isReady));
    }
}
