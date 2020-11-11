package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.Main;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.util.PropertyReaderUtil;

public class ReInitDaemonThread extends Thread {
    @Override
    public void run() {
        Main.logger.info("Daemon thread was started");
        while (true) {
            int refreshRate = PropertyReaderUtil.loadProperties().getFileRefreshRate() * 1000;
            try {
                NassaContext.getInstance().init();
                Thread.sleep(refreshRate);
            } catch (InterruptedException | InvalidStateException e) {
                e.printStackTrace();
            }
        }
    }
}
