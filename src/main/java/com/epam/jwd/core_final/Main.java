package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.exception.InvalidStateException;

public class Main {

    public static void main(String[] args) throws InvalidStateException {
        ApplicationMenu menu = Application.start();
//        menu.printAvailableOptions();
//
//        ApplicationMenu menu1 = () -> {
//            NassaContext nassaContext = new NassaContext();
//            nassaContext.init();
//            return nassaContext;
//        };

    }
}