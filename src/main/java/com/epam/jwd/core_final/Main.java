package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.menu.ApplicationMenu;
import com.epam.jwd.core_final.context.menu.Menu;
import com.epam.jwd.core_final.exception.MissionNotCreatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    public static Logger logger = LoggerFactory.getLogger(Menu.class);

    public static void main(String[] args) throws MissionNotCreatedException {

        ApplicationMenu menu = Application.start();

        System.out.println("Welcome to space exploration manager. Please, choose available options:");
        menu.printAvailableOptions();
        menu.handleUserInput();
    }
}