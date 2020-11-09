package com.epam.jwd.core_final.context.menu;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.impl.NassaContext;

import java.util.Scanner;

public abstract class Menu {
    protected static Scanner scanner = new Scanner(System.in);
    abstract public void printAvailableOptions();
    abstract public void handleUserInput();
}
