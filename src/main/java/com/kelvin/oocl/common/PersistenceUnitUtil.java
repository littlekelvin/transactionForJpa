package com.kelvin.oocl.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PersistenceUnitUtil {
    private static List<String> puIds = new ArrayList<>();

    static {
        try {
            Properties properties = new Properties();
            properties.load(PersistenceUnitUtil.class.getClassLoader().getResourceAsStream("persistenceUnitConfig.properties"));
            String[] ids = properties.getProperty("persistence.unit.ids").split(",");
            if (null != ids && ids.length > 0) {
                for (int i = 0; i < ids.length; i++) {
                    puIds.add(ids[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getPersistenceUnitIds() {
        return puIds;
    }
}
