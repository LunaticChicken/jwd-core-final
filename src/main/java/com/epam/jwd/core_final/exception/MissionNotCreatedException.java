package com.epam.jwd.core_final.exception;

public class MissionNotCreatedException extends Exception {
    public MissionNotCreatedException() {
    }

    public MissionNotCreatedException(String message) {
        super(message);
    }

    public MissionNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public MissionNotCreatedException(Throwable cause) {
        super(cause);
    }
}
