package pl.edu.pw.ee.struct.map;

import pl.edu.pw.ee.binary.io.BitWriter;

import java.util.ArrayList;
import java.util.function.Consumer;

import static pl.edu.pw.ee.struct.map.Color.BLACK;
import static pl.edu.pw.ee.struct.map.Color.RED;

public class RedBlackTree<V> {
    private Node<V> root;

    public V get(byte[] key) {
        validateKey(key);
        Node node = root;

        V result = null;

        while (node != null) {
            int compare = BitWriter.compareByteArrays(key, node.getKey());

            if (compare < 0) {
                node = node.getLeft();

            } else if (compare > 0) {
                node = node.getRight();

            } else {
                result = (V) node.getValue();
                break;
            }
        }
        return result;
    }

    public void put(byte[] key, V value) {
        validateParams(key, value);
        root = put(root, key, value);
        root.setColor(BLACK);
    }

    public void deleteMin() {
        if (root == null) {
            return;
        }

        root = deleteMin(root);

        if (root != null) {
            root.setColor(BLACK);
        }
    }

    private void validateKey(byte[] key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
    }

    private Node<V> deleteMin(Node<V> node) {
        if (node == null) {
            return null;
        }

        if (node.getLeft() == null) {
            return null;
        }

        if (isBlack(node.getLeft()) && isBlack(node.getLeft().getLeft())) {
            node = moveRedLeft(node);
        }

        node.setLeft(deleteMin(node.getLeft()));

        return reorganizeTree(node);
    }

    private Node<V> moveRedLeft(Node<V> node) {
        changeColors(node);

        if (isRed(node.getRight()) && isRed(node.getRight().getLeft())) {
            node.setRight(rotateRight(node.getRight()));
            node = rotateLeft(node);
            changeColors(node);
        }
        return node;
    }

    private void validateParams(byte[] key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Invalid input params - key and value cannot be null");
        }
    }

    private Node<V> put(Node<V> node, byte[] key, V value) {
        if (node == null) {
            return new Node<>(key, value);
        }

        int comparator = BitWriter.compareByteArrays(key, node.getKey());

        if (comparator > 0) {
            putOnTheRight(node, key, value);

        } else if (comparator < 0) {
            putOnTheLeft(node, key, value);

        } else {
            node.setValue(value);
        }

        node = reorganizeTree(node);

        return node;
    }

    private void putOnTheRight(Node<V> node, byte[] key, V value) {
        Node<V> rightChild = put(node.getRight(), key, value);
        node.setRight(rightChild);
    }

    private void putOnTheLeft(Node<V> node, byte[] key, V value) {
        Node<V> leftChild = put(node.getLeft(), key, value);
        node.setLeft(leftChild);
    }

    private Node<V> reorganizeTree(Node<V> node) {
        node = rotateLeftIfNeeded(node);
        node = rotateRightIfNeeded(node);
        changeColorsIfNeeded(node);

        return node;
    }

    private Node<V> rotateLeftIfNeeded(Node<V> node) {
        if (isBlack(node.getLeft()) && isRed(node.getRight())) {
            node = rotateLeft(node);
        }
        return node;
    }

    private Node<V> rotateLeft(Node<V> node) {
        Node head = node.getRight();
        node.setRight(head.getLeft());
        head.setLeft(node);
        head.setColor(node.getColor());
        node.setColor(RED);

        return head;
    }

    private Node<V> rotateRightIfNeeded(Node<V> node) {
        if (isRed(node.getLeft()) && isRed(node.getLeft().getLeft())) {
            node = rotateRight(node);
        }
        return node;
    }

    private Node<V> rotateRight(Node<V> node) {
        Node<V> x = node.getLeft();
        node.setLeft(x.getRight());
        x.setRight(node);
        x.setColor(node.getColor());
        node.setColor(RED);
        return x;
    }

    private void changeColorsIfNeeded(Node<V> node) {
        if (isRed(node.getLeft()) && isRed(node.getRight())) {
            changeColors(node);
        }
    }

    private void changeColors(Node<V> node) {
        swapColor(node);
        swapColor(node.getLeft());
        swapColor(node.getRight());
    }

    private void swapColor(Node<V> node) {
        if (node.isRed()) {
            node.setColor(BLACK);
        } else {
            node.setColor(RED);
        }
    }

    private boolean isBlack(Node<V> node) {
        return !isRed(node);
    }

    private boolean isRed(Node<V> node) {
        return node == null
                ? false
                : node.isRed();
    }

    public void printMap() {
        printInOrder(root);
    }

    private void printInOrder(Node<V> node) {
        if (node == null) {
            return;
        }
        printInOrder(node.getLeft());
        String s = "";
        for (byte b : node.getKey())
            s = s + (char) b;
        System.out.println(s + " = " + node.getValue());
        printInOrder(node.getRight());
    }

    public void inorderWithFunction(Consumer<Node<V>> action) {
        ArrayList<Node<V>> stack = new ArrayList<>();
        Node<V> current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.add(current);
                current = current.getLeft();
            }

            current = stack.remove(stack.size() - 1);
            action.accept(current);
            current = current.getRight();
        }
    }
}
