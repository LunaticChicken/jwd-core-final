package com.epam.jwd.core_final.context.menu;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.util.ValidInputUtil;

public final class CrewFiltersMenu extends Menu {

    private static CrewFiltersMenu instance;

    public static CrewFiltersMenu getInstance() {
        if (instance == null) {
            instance = new CrewFiltersMenu();
        }
        return instance;
    }

    @Override
    public void printAvailableOptions() {
        System.out.println("1 - Set id filter\n"
                + "2 - Set name filter\n"
                + "3 - Set role filter\n"
                + "4 - Set rank filter\n"
                + "5 - Set ready filter\n"
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
                    System.out.print("Enter role: ");
                    Role role = ValidInputUtil.getValidRole(scanner);
                    setRoleFilter(role);
                    break;
                case 4:
                    System.out.print("Enter rank: ");
                    Rank rank = ValidInputUtil.getValidRank(scanner);
                    setRankFilter(rank);
                    break;
                case 5:
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
            System.out.println("Filter added.");
        }
    }

    private void setIdFilter(Long id) {
        NassaContext.getInstance().setCrewCriteria(CrewMemberCriteria.getInstance().id(id));
    }

    private void setNameFilter(String name) {
        NassaContext.getInstance().setCrewCriteria(CrewMemberCriteria.getInstance().name(name));
    }

    private void setRoleFilter(Role role) {
        NassaContext.getInstance().setCrewCriteria(CrewMemberCriteria.getInstance().role(role));
    }

    private void setRankFilter(Rank rank) {
        NassaContext.getInstance().setCrewCriteria(CrewMemberCriteria.getInstance().rank(rank));
    }

    private void setReadyFilter(Boolean isReady) {
        NassaContext.getInstance().setCrewCriteria(CrewMemberCriteria.getInstance().isReadyForNextMission(isReady));
    }
}
