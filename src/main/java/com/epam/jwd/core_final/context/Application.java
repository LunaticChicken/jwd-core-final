package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.exception.InvalidStateException;

import java.io.File;
import java.util.List;
import java.util.function.Supplier;

public interface Application {

    static ApplicationMenu start() throws InvalidStateException {
         // todo
        final Supplier<ApplicationContext> applicationContextSupplier = NassaContext::getInstance;

        NassaContext.getInstance().init();
        return applicationContextSupplier::get;
    }
}
