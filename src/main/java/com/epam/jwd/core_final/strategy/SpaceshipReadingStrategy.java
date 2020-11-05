package com.epam.jwd.core_final.strategy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SpaceshipReadingStrategy implements ReadingStrategy {

    private static SpaceshipReadingStrategy instance;

    public static SpaceshipReadingStrategy getInstance() {
        if (instance == null) {
            instance = new SpaceshipReadingStrategy();
        }
        return instance;
    }

    @Override
    public String read(File file) {
        StringBuilder spaceshipsInFile = new StringBuilder("");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine();
            reader.readLine();
            reader.readLine();
            String spaceship;
            while ((spaceship = reader.readLine()) != null) {
                spaceshipsInFile.append(spaceship).append("\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return spaceshipsInFile.toString();
    }
}
