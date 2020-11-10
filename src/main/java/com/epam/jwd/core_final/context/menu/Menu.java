package com.epam.jwd.core_final.context.menu;

import com.epam.jwd.core_final.exception.MissionNotCreatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public abstract class Menu {
    protected static Scanner scanner = new Scanner(System.in);
    public static Logger logger = LoggerFactory.getLogger(Menu.class);

    abstract public void printAvailableOptions();
    abstract public void handleUserInput() throws MissionNotCreatedException;
}
