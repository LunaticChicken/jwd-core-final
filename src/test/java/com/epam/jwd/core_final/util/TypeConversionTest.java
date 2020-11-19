package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class TypeConversionTest {

    @Test
    public void testCheckIfStringEqualsDateTimeFormat() {
        String stringDateTime = "2020-12-12 12:12:12";
        LocalDateTime requiredDateTime = LocalDateTime.of(2020, 12, 12, 12, 12 ,12);
        Assert.assertEquals(TypeConversionUtil.stringToLocalDateTime(stringDateTime), requiredDateTime);
    }

    @Test
    public void testCheckIfStringEqualsRank() {
        String stringRank = "2";
        Rank requiredRank = Rank.SECOND_OFFICER;
        Assert.assertEquals(TypeConversionUtil.stringToRank(stringRank), requiredRank);
    }

    @Test
    public void testCheckIfStringEqualsRole() {
        String stringRole = "4";
        Role requiredRole = Role.COMMANDER;
        Assert.assertEquals(TypeConversionUtil.stringToRole(stringRole), requiredRole);
    }

    @Test
    public void testCheckIfStringEqualsStatus() {
        String stringStatus = "1";
        Assert.assertTrue(TypeConversionUtil.stringToStatus(stringStatus));
    }
}
