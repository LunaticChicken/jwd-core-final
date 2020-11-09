package com.epam.jwd.core_final.context.menu;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.MissionCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import com.epam.jwd.core_final.util.ValidInputUtil;

public final class InfoMenu extends Menu {
    private static InfoMenu instance;

    public static InfoMenu getInstance() {
        if (instance == null) {
            instance = new InfoMenu();
        }
        return instance;
    }

    @Override
    public void printAvailableOptions() {
        System.out.println("1 - Show crew members\n"
                + "2 - Show spaceship\n"
                + "3 - Show missions\n"
                + "0 - Back");
    }

    @Override
    public void handleUserInput() {
        externalLoop:
        while (true) {
            int option = ValidInputUtil.getValidIntNumber(scanner);
            switch (option) {
                case 1:
                    showCrewMembersWithFilters();
                    break;
                case 2:
                    showSpaceshipsWithFilters();
                    break;
                case 3:
                    showMissionsWithFilters();
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

    private static void showCrewMembersWithFilters() {
        System.out.println(CrewServiceImpl.getInstance().
                findAllCrewMembersByCriteria((CrewMemberCriteria) NassaContext.getInstance().getCrewCriteria()));
    }

    private static void showSpaceshipsWithFilters() {
        System.out.println(SpaceshipServiceImpl.getInstance().
                findAllSpaceshipsByCriteria((SpaceshipCriteria) NassaContext.getInstance().getSpaceshipCriteria()));
    }

    private static void showMissionsWithFilters() {
        System.out.println(MissionServiceImpl.getInstance().
                findAllMissionsByCriteria((MissionCriteria) NassaContext.getInstance().getMissionCriteria()));
    }
}
