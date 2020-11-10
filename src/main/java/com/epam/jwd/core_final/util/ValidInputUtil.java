package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Scanner;

public final class ValidInputUtil {

    public static Integer getValidIntNumber(Scanner scanner) {
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }
            System.out.println("You should input a number! Try again: ");
            scanner.next();
        }
        return null;
    }

    public static Long getValidLongNumber(Scanner scanner) {
        while (scanner.hasNext()) {
            if (scanner.hasNextLong()) {
                return scanner.nextLong();
            }
            System.out.println("You should input a number! Try again:");
            scanner.nextLong();
        }
        return null;
    }

    public static Role getValidRole(Scanner scanner) {
        String input;
        while (true) {
            input = scanner.next();
            if (input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4")) break;
            System.out.println("You should input 1-4 number! Try again:");
        }
        return TypeConversionUtil.stringToRole(input);
    }

    public static Rank getValidRank(Scanner scanner) {
        String input;
        while (true) {
            input = scanner.next();
            if (input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4")) break;
            System.out.println("You should input 1-4 number! Try again:");
        }
        return TypeConversionUtil.stringToRank(input);
    }

    public static Boolean getReadyStatus(Scanner scanner) {
        while (true) {
            Boolean isReady = TypeConversionUtil.stringToStatus(scanner.nextLine());
            if (isReady == null) {
                System.out.println("You should input 0 or 1! Try again:");
            } else {
                return isReady;
            }
        }
    }

    public static boolean isInputEqualsDateFormat(String input) {
        String dateFormat = PropertyReaderUtil.loadProperties().getDateTimeFormat();
        if (input.length() != dateFormat.length()) {
            return false;
        }
        int year = 0, month = 0, day = 0, hour = 0, minute = 0, second = 0;
        try {
            for (int i = 0; i < dateFormat.length(); i++) {
                switch (dateFormat.charAt(i)) {
                    case 'y':
                        year = Integer.parseInt(input.substring(i, i + 4));
                        i += 4;
                        break;
                    case 'M':
                        month = Integer.parseInt(input.substring(i, i + 2));
                        i += 2;
                        break;
                    case 'd':
                        day = Integer.parseInt(input.substring(i, i + 2));
                        i += 2;
                        break;
                    case 'H':
                        hour = Integer.parseInt(input.substring(i, i + 2));
                        i += 2;
                        break;
                    case 'm':
                        minute = Integer.parseInt(input.substring(i, i + 2));
                        i += 2;
                        break;
                    case 's':
                        second = Integer.parseInt(input.substring(i, i + 2));
                        i += 2;
                        break;
                }
            }
        } catch (NumberFormatException e) {
            return false;
        }
        try {
            LocalDateTime.of(year, month, day, hour, minute, second);
        } catch (DateTimeException e) {
            return false;
        }
        return true;
    }
}
