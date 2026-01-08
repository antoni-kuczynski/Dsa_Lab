package pl.edu.pw.ee.aisd2025zex7.data.input;

import static java.lang.String.format;
import static java.util.Objects.isNull;
import java.util.logging.Logger;

public enum GraphType {

    DIRECTED,
    UNDIRECTED;

    private static final Logger LOG = Logger.getLogger(GraphType.class.getName());

    public static GraphType toType(String typeStr) {
        if (isNull(typeStr)) {
            throw new IllegalArgumentException("Input string (as graph type) cannot be null!");
        }

        GraphType result;

        try {
            typeStr = typeStr.toUpperCase();
            result = GraphType.valueOf(typeStr);

        } catch (IllegalArgumentException e) {
            LOG.severe(format("Unknown graph type: \"%s\"!", typeStr));
            throw e;
        }

        return result;
    }
}
