package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

import java.time.LocalDateTime;

public final class TypeConversionUtil {
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
                return Rank.SECOND_OFFICER;
            case "3":
                return Rank.FIRST_OFFICER;
            case "4":
                return Rank.CAPTAIN;
            default:
                return null;
        }
    }

    public static Boolean stringToStatus(String status) {
        switch (status) {
            case "0":
                return false;
            case "1":
                return true;
            default:
                return null;
        }
    }

    public static LocalDateTime stringToLocalDateTime(String date) {
        String dateFormat = PropertyReaderUtil.loadProperties().getDateTimeFormat();
        int year = Integer.parseInt(date.substring(dateFormat.indexOf("y"), dateFormat.indexOf("y") + 4));
        int month = Integer.parseInt(date.substring(dateFormat.indexOf("M"), dateFormat.indexOf("M") + 2));
        int day = Integer.parseInt(date.substring(dateFormat.indexOf("d"), dateFormat.indexOf("d") + 2));
        int hour = Integer.parseInt(date.substring(dateFormat.indexOf("H"), dateFormat.indexOf("H") + 2));
        int minute = Integer.parseInt(date.substring(dateFormat.indexOf("m"), dateFormat.indexOf("m") + 2));
        int second = Integer.parseInt(date.substring(dateFormat.indexOf("s"), dateFormat.indexOf("s") + 2));
        return LocalDateTime.of(year, month, day, hour, minute, second);
    }

}
