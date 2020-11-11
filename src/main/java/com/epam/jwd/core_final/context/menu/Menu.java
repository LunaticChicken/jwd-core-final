package com.epam.jwd.core_final.context.menu;

import com.epam.jwd.core_final.exception.MissionNotCreatedException;

import java.util.Scanner;

public abstract class Menu {
    protected static Scanner scanner = new Scanner(System.in);

    abstract public void printAvailableOptions();

    abstract public void handleUserInput() throws MissionNotCreatedException;
}
