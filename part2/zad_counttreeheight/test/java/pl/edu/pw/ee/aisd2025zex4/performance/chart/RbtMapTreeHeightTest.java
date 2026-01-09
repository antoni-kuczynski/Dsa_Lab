package pl.edu.pw.ee.aisd2025zex4.performance.chart;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.String.format;
import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.logging.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex4.Node;
import pl.edu.pw.ee.aisd2025zex4.RbtMap;
import pl.edu.pw.ee.aisd2025zex4.service.MapInterface;
public abstract class RbtMapTreeHeightTest {

    private static final Logger LOG = Logger.getLogger(RbtMapTreeHeightTest.class.getName());
    private final String filename;

    static final int MAX_SIZE = 1_000_000;

    public RbtMapTreeHeightTest(String filename) {
        this.filename = filename;
    }

    @BeforeEach
    public void setup() {
        removeFileBeforeStart();
    }






    private Node<String, Integer> getRoot(MapInterface<String, Integer> map) {
        Object rbtTree = getTree(map);

        try {
            Field rootField = rbtTree.getClass().getDeclaredField("root");
            rootField.setAccessible(true);
            Object root = rootField.get(rbtTree);

            if (root.getClass() != Node.class) {
                return null;
            }

            return (Node<String, Integer>) root;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private int countRedNodes(Node<String, Integer> root) {
        if (root == null) {
            return 0;
        }

        int redCount = 0;
        ArrayDeque<Node<String, Integer>> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node<String, Integer> node = stack.pop();

            if (node.isRed()) {
                redCount++;
            }
            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }
            if (node.getRight() != null) {
                stack.push(node.getRight());
            }
        }
        return redCount;
    }

    @Test
    public void countTreeHeightDependingOnDataSize() {
        MapInterface<String, Integer> map = new RbtMap<>();
        int currentSize = 0;
        int step = 100;

        int nOfPuts;

        while (currentSize < MAX_SIZE) {
            putData(map, currentSize, step);
            currentSize += step;

            nOfPuts = getNumOfPuts(map);
            saveResult(currentSize, nOfPuts);
        }

        int redNodes = countRedNodes(getRoot(map));
        System.out.println("Count of red nodes = " + redNodes + " for test: " + getClass().getSimpleName());
    }









    abstract void putData(MapInterface<String, Integer> map, int currentSize, int step);

    private void removeFileBeforeStart() {
        File f = new File(filename);

        boolean isDeleted = f.delete();

        if (isDeleted) {
            LOG.info(format("Removed file [filename: %s].", filename));
        } else {
            LOG.warning(format("Cannot remove file. File does not exist. [filename: %s].", filename));
        }
    }

    private void saveResult(int currentSize, int nOfPuts) {
        File f = new File(filename);

        try (FileWriter fw = new FileWriter(f, true); BufferedWriter writer = new BufferedWriter(fw)) {
            writer.append(format("size: %7d | nOfPuts: %d\n", currentSize, nOfPuts));

        } catch (IOException e) {
            LOG.severe("Caught an error while saving results.");
            throw new RuntimeException(e);
        }
    }

    private int getNumOfPuts(MapInterface<String, Integer> map) {
        String fieldNumOfPuts = "currentNumOfPut";

        try {
            Object rbtTree = getTree(map);

            Field currentNumOfPut = rbtTree.getClass().getDeclaredField(fieldNumOfPuts);
            currentNumOfPut.setAccessible(true);

            int numOfPuts = currentNumOfPut.getInt(rbtTree);

            return numOfPuts;

        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Object getTree(MapInterface<String, Integer> map) {
        String fieldTree = "tree";

        try {
            Field tree = map.getClass().getDeclaredField(fieldTree);
            tree.setAccessible(true);

            Object rbtTree = tree.get(map);

            return rbtTree;

        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            LOG.severe("Cannot get tree instance from map instance.");
            throw new RuntimeException(e);
        }
    }

}
