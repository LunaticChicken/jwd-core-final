package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.exception.InvalidStateException;

import java.io.File;
import java.util.Collection;
import java.util.List;

public interface ApplicationContext {

    <T extends BaseEntity>Collection<T> retrieveBaseEntityList(Class<T> tClass);

    void init() throws InvalidStateException;

}
