package com.epam.jwd.core_final.strategy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CrewReadingStrategy implements ReadingStrategy {

    private static CrewReadingStrategy instance;

    public static CrewReadingStrategy getInstance() {
        if (instance == null) {
            instance = new CrewReadingStrategy();
        }
        return instance;
    }

    @Override
    public String read(File file) {
        String crewInFile = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine();
            crewInFile = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return crewInFile;
    }
}
