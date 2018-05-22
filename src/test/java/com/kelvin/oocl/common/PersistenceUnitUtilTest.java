package com.kelvin.oocl.common;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PersistenceUnitUtilTest {
    @Test
    public void getPersistenceUnitIds() throws Exception {
        List<String> ids = PersistenceUnitUtil.getPersistenceUnitIds();
        Assert.assertTrue(!ids.isEmpty() && ids.size()==2);
    }

}