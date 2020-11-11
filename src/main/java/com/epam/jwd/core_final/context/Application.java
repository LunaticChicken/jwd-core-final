package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.ReInitDaemonThread;
import com.epam.jwd.core_final.context.menu.ApplicationMenu;
import com.epam.jwd.core_final.exception.InvalidStateException;

public interface Application {

    static ApplicationMenu start() throws InvalidStateException {
        // todo
        ReInitDaemonThread daemonReInitThread = new ReInitDaemonThread();
        daemonReInitThread.setDaemon(true);
        daemonReInitThread.start();
        return ApplicationMenu.getInstance();
    }
}
