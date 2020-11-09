package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.menu.ApplicationMenu;
import com.epam.jwd.core_final.exception.InvalidStateException;

public class Main {

    public static void main(String[] args) throws InvalidStateException {
        ApplicationMenu menu = Application.start();

        System.out.println("Welcome to space exploration manager. Please, choose available options:");
        menu.printAvailableOptions();
        menu.handleUserInput();
    }
}