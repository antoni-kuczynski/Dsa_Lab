package pl.edu.pw.ee.struct.map;

import static pl.edu.pw.ee.struct.map.Color.*;

public class Node<V> {

    private byte[] key;
    private V value;
    private Node<V> left, right;
    private Color color;

    public Node(byte[] key, V value) {
        this.key = key;
        this.value = value;
        this.color = RED;
    }

    public boolean isRed() {
        return RED.equals(color);
    }

    public byte[] getKey() {
        return key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public Node<V> getLeft() {
        return left;
    }

    public void setLeft(Node<V> leftNode) {
        left = leftNode;
    }

    public Node<V> getRight() {
        return right;
    }

    public void setRight(Node<V> rightNode) {
        right = rightNode;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
