package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

public class TypeConversionUtil {
    public static Role stringToRole(String role) {
        switch (role) {
            case "1":
                return Role.MISSION_SPECIALIST;
            case "2":
                return Role.FLIGHT_ENGINEER;
            case "3":
                return Role.PILOT;
            case "4":
                return Role.COMMANDER;
            default:
                return null;
        }
    }

    public static Rank stringToRank(String rank) {
        switch (rank) {
            case "1":
                return Rank.TRAINEE;
            case "2":
                return Rank.FIRST_OFFICER;
            case "3":
                return Rank.SECOND_OFFICER;
            case "4":
                return Rank.CAPTAIN;
            default:
                return null;
        }
    }
}
