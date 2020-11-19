package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.util.PropertyReaderUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class ReInitTest {

    @BeforeClass
    public static void init() throws InvalidStateException {
        NassaContext.getInstance().init();
    }

    @Test
    public void testCheckIfCollectionIsUpdatedAfterUpdatingFile() throws InvalidStateException {
        Collection<CrewMember> crewBeforeUpdating = new ArrayList<>(
                NassaContext.getInstance().retrieveBaseEntityList(CrewMember.class));

        String crewPath = getCrewPath();
        String crewTextBeforeUpdating = read(crewPath);

        write(crewPath, "2,Sally Gonzales,4;", true);

        NassaContext.getInstance().init();

        Collection<CrewMember> crewAfterUpdating =
                NassaContext.getInstance().retrieveBaseEntityList(CrewMember.class);
        write(crewPath, crewTextBeforeUpdating, false);
        Assert.assertNotEquals(crewBeforeUpdating, crewAfterUpdating);
    }

    private String getCrewPath() {
        return PropertyReaderUtil.loadProperties().getInputRootDir()
                + "/"
                + PropertyReaderUtil.loadProperties().getCrewFileName();
    }

    private void write(String path, String input, boolean append) {
        try (FileWriter writer = new FileWriter(path, append)) {
            writer.write(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String read(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            return reader.readLine() + "\n" + reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
