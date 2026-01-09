package pl.edu.pw.ee.struct.map;

import java.util.ArrayList;

public class RbtMap<V> implements MapInterface<V> {

    private final RedBlackTree<V> tree;

    public RbtMap() {
        tree = new RedBlackTree();
    }

    @Override
    public void put(byte[] key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Params (key, value) cannot be null.");
        }
        tree.put(key, value);
    }

    @Override
    public V get(byte[] key) {
        if (key == null) {
            throw new IllegalArgumentException("Cannot get value by null key.");
        }
        return tree.get(key);
    }

    public ArrayList<byte[]> keys() {
        ArrayList<byte[]> keys = new ArrayList<>();
        tree.inorderWithFunction(kvNode -> keys.add(kvNode.getKey()));
        return keys;
    }

    public void printMap() {
        tree.printMap();
    }
}
