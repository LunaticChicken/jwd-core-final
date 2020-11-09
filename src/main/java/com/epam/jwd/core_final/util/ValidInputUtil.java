package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

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
        // format = "yyyy-MM-dd HH:mm:ss"
        // input = "2020-12-12 12:12:12"
        // input = "12-12-2002 12/12/12"
        char[] availableSplitters = {':', '-', ' ', '.', '/'};
        for (int i = 0; i < input.length(); i++) {
            for (char availableSplitter : availableSplitters) {
                if (input.charAt(i) == availableSplitter && dateFormat.charAt(i) != availableSplitter) {
                    return false;
                }
            }
        }
        return true;
    }
}
