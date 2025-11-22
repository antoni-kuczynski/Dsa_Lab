package pl.edu.pw.ee.aisd2025zex4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RedBlackTreeTest {
    private RedBlackTree<Integer, String> tree;

    @BeforeEach
    void setUp() {
        tree = new RedBlackTree<>();
    }

    private boolean containsTwoConsecutiveRedNodes() {
        ArrayDeque<Node<Integer, String>> stack = new ArrayDeque<>();

        Node<Integer, String> node = tree.getRoot();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }

            node = stack.pop();

            if (!node.isRed()) {
                node = node.getRight();
                continue;
            }

            if (node.getRight() != null && node.getRight().isRed()) {
                return true;
            }

            if (node.getLeft() != null && node.getLeft().isRed()) {
                return true;
            }
            node = node.getRight();
        }
        return false;
    }

    private boolean areBlackPathsTheSameLength() {
        ArrayList<Integer> blackCounts = new ArrayList<>();
        countBlackNodesOnAllPaths(tree.getRoot(), 0, blackCounts);
        return blackCounts.stream().distinct().count() == 1;
    }

    private void countBlackNodesOnAllPaths(Node<Integer, String> node, int count, ArrayList<Integer> blackCounts) {
        if (node == null) {
            blackCounts.add(count);
            return;
        }

        if (!node.isRed()) {
            count++;
        }

        countBlackNodesOnAllPaths(node.getLeft(), count, blackCounts);
        countBlackNodesOnAllPaths(node.getRight(), count, blackCounts);
    }

    @Test
    public void should_MaintainRedBlackProperties_AfterDeleteMin() {
        for (int i = 1; i < 20; i++) {
            tree.put(i, "val" + i);
        }

        tree.deleteMin();
        tree.deleteMin();

        boolean isRootBlack = tree.getRoot().getColor().equals(Color.BLACK);
        boolean containsTwoConsecutiveRedNodes = containsTwoConsecutiveRedNodes();
        boolean areAllBlackPathsFromRootTheSameLength = areBlackPathsTheSameLength();


        assertTrue(isRootBlack, "The root is not black");
        assertFalse(containsTwoConsecutiveRedNodes, "A son of a red node is red which is invalid");
        assertTrue(areAllBlackPathsFromRootTheSameLength, "The black path lengths are not the same which is invalid");
    }

    @Test
    public void should_CorrectlyRemoveMinNode() {
        for (int i = 6; i < 25; i++) {
            tree.put(i, "val" + i);
        }

        tree.deleteMin(); //val1

        assertNull(tree.get(1));

    }

    @Test
    public void should_NotPut_WhenValueAndKeyAreNull() {
        assertThrows(IllegalArgumentException.class,
                () -> tree.put(null, null));
    }

    @Test
    public void should_PutCorrectly_AsRoot() {
        //given
        tree.put(1, "asdsdfa");

        //when
        String value = tree.get(1);

        //then
        assertEquals("asdsdfa", value);
    }

    @Test
    public void should_PutCorrectly_OnTheLeft() {
        //given
        tree.put(10, "a");
        tree.put(1, "asdsdfa");

        //when
        String value = tree.get(1);

        //then
        assertEquals("asdsdfa", value);
    }

    @Test
    public void should_PutCorrectly_OnTheRight() {
        //given
        tree.put(10, "a");
        tree.put(1000, "asdsdfa");

        //when
        String value = tree.get(1000);

        //then
        assertEquals("asdsdfa", value);
    }
}