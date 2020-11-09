package com.epam.jwd.core_final.context.menu;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.MissionCriteria;
import com.epam.jwd.core_final.util.TypeConversionUtil;
import com.epam.jwd.core_final.util.ValidInputUtil;

import java.time.LocalDateTime;

public final class MissionFiltersMenu extends Menu {

    private static MissionFiltersMenu instance;

    private MissionFiltersMenu() {
    }

    public static MissionFiltersMenu getInstance() {
        if (instance == null) {
            instance = new MissionFiltersMenu();
        }
        return instance;
    }

    @Override
    public void printAvailableOptions() {
        System.out.println("1 - Set id filter\n"
                + "2 - Set name filter\n"
                + "3 - Set start date filter\n"
                + "4 - Set end date filter\n"
                + "5 - Set mission distance\n"
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
                    System.out.print("Enter start date: ");
                    LocalDateTime startDate = TypeConversionUtil.stringToLocalDateTime(scanner.nextLine());
                    setStartDateFilter(startDate);
                    break;
                case 4:
                    System.out.print("Enter end date: ");
                    LocalDateTime endDate = TypeConversionUtil.stringToLocalDateTime(scanner.nextLine());
                    setEndDateFilter(endDate);
                    break;
                case 5:
                    System.out.print("Enter mission distance: ");
                    Long distance = ValidInputUtil.getValidLongNumber(scanner);
                    setDistanceFilter(distance);
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
        NassaContext.getInstance().setMissionCriteria(MissionCriteria.getInstance().id(id));
    }

    private void setNameFilter(String name) {
        NassaContext.getInstance().setMissionCriteria(MissionCriteria.getInstance().name(name));
    }

    private void setStartDateFilter(LocalDateTime startDate) {
        NassaContext.getInstance().setMissionCriteria(MissionCriteria.getInstance().startDate(startDate));
    }

    private void setEndDateFilter(LocalDateTime endDate) {
        NassaContext.getInstance().setMissionCriteria(MissionCriteria.getInstance().startDate(endDate));
    }

    private void setDistanceFilter(Long distance) {
        NassaContext.getInstance().setMissionCriteria(MissionCriteria.getInstance().missionDistance(distance));
    }

}
