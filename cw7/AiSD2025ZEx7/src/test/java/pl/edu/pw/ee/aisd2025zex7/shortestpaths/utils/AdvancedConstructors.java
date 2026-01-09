package pl.edu.pw.ee.aisd2025zex7.shortestpaths.utils;

import java.lang.reflect.InvocationTargetException;
import pl.edu.pw.ee.aisd2025zex7.shortestpaths.ShortestPaths;

public class AdvancedConstructors {

    public static ShortestPaths createShorhestPathAlgorithm(Class<? extends ShortestPaths> clazz) {
        ShortestPaths algorithmInstance;

        try {
            algorithmInstance = (ShortestPaths) clazz.getDeclaredConstructor().newInstance();

        } catch (InstantiationException
                | IllegalAccessException
                | NoSuchMethodException
                | SecurityException
                | IllegalArgumentException
                | InvocationTargetException e) {
            throw new RuntimeException("Cannot create instance (ShortestPaths) by reflection!", e);
        }

        return algorithmInstance;
    }
}
