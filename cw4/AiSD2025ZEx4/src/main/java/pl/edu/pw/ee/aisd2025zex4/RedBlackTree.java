package pl.edu.pw.ee.aisd2025zex4;

import static pl.edu.pw.ee.aisd2025zex4.Color.BLACK;
import static pl.edu.pw.ee.aisd2025zex4.Color.RED;
public class RedBlackTree<K extends Comparable<K>, V> {
    private Node<K, V> root;
    private int currentNumOfPut = 0;

    public V get(K key) {
        validateKey(key);
        Node<K, V> node = root;

        V result = null;

        while (node != null) {

            if (shouldCheckOnTheLeft(key, node)) {
                node = node.getLeft();

            } else if (shouldCheckOnTheRight(key, node)) {
                node = node.getRight();

            } else {
                result = node.getValue();
                break;
            }
        }
        return result;
    }

    public void put(K key, V value) {
        validateParams(key, value);
        currentNumOfPut = 0;
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

    private void validateKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
    }

    private Node<K, V> deleteMin(Node<K, V> node) {
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

    private Node<K, V> moveRedLeft(Node<K, V> node) {
        changeColors(node);

        if (isRed(node.getRight()) && isRed(node.getRight().getLeft())) {
            node.setRight(rotateRight(node.getRight()));
            node = rotateLeft(node);
            changeColors(node);
        }
        return node;
    }


    private boolean shouldCheckOnTheLeft(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private boolean shouldCheckOnTheRight(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private void validateParams(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Input params (key, value) cannot be null.");
        }
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        currentNumOfPut++;

        if (node == null) {
            return new Node<>(key, value);
        }

        if (isKeyBiggerThanNode(key, node)) {
            putOnTheRight(node, key, value);

        } else if (isKeySmallerThanNode(key, node)) {
            putOnTheLeft(node, key, value);

        } else {
            node.setValue(value);
        }

        node = reorganizeTree(node);

        return node;
    }

    private boolean isKeyBiggerThanNode(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private void putOnTheRight(Node<K, V> node, K key, V value) {
        Node<K, V> rightChild = put(node.getRight(), key, value);
        node.setRight(rightChild);
    }

    private boolean isKeySmallerThanNode(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private void putOnTheLeft(Node<K, V> node, K key, V value) {
        Node<K, V> leftChild = put(node.getLeft(), key, value);
        node.setLeft(leftChild);
    }

    private Node<K, V> reorganizeTree(Node<K, V> node) {
        node = rotateLeftIfNeeded(node);
        node = rotateRightIfNeeded(node);
        changeColorsIfNeeded(node);

        return node;
    }

    private Node<K, V> rotateLeftIfNeeded(Node<K, V> node) {
        if (isBlack(node.getLeft()) && isRed(node.getRight())) {
            node = rotateLeft(node);
        }
        return node;
    }

    private Node<K, V> rotateLeft(Node<K, V> node) {
        Node<K, V> head = node.getRight();  //TODO: NULL CHECKS
        node.setRight(head.getLeft());
        head.setLeft(node);
        head.setColor(node.getColor());
        node.setColor(RED);

        return head;
    }

    private Node<K, V> rotateRightIfNeeded(Node<K, V> node) {
        if (isRed(node.getLeft()) && isRed(node.getLeft().getLeft())) {
            node = rotateRight(node);
        }
        return node;
    }

    private Node<K, V> rotateRight(Node<K, V> node) {
        Node<K, V> x = node.getLeft();  //TODO: NULL CHECKS
        node.setLeft(x.getRight());
        x.setRight(node);
        x.setColor(node.getColor());
        node.setColor(RED);
        return x;
    }

    private void changeColorsIfNeeded(Node<K, V> node) {
        if (isRed(node.getLeft()) && isRed(node.getRight())) {
            changeColors(node);
        }
    }

    private void changeColors(Node<K, V> node) {
        swapColor(node);
        swapColor(node.getLeft());
        swapColor(node.getRight());
    }

    private void swapColor(Node<K, V> node) {
        if (node.isRed()) {
            node.setColor(BLACK);
        } else {
            node.setColor(RED);
        }
    }

    private boolean isBlack(Node<K, V> node) {
        return !isRed(node);
    }

    private boolean isRed(Node<K, V> node) {
        return node == null
                ? false
                : node.isRed();
    }

    public Node<K, V> getRoot() {
        return root;
    }













    public void delete(K key) {
        validateKey(key);
        if (root == null) {
            return;
        }

        // jeśli oba dzieci root są czarne, ustaw root na czerwony
        if (!isRed(root.getLeft()) && !isRed(root.getRight())) {
            root.setColor(RED);
        }

        root = delete(root, key);

        if (root != null) {
            root.setColor(BLACK);
        }
    }

    private Node<K, V> delete(Node<K, V> node, K key) {
        if (key.compareTo(node.getKey()) < 0) {
            if (node.getLeft() != null) {
                if (isBlack(node.getLeft()) && isBlack(node.getLeft().getLeft())) {
                    node = moveRedLeft(node);
                }
                node.setLeft(delete(node.getLeft(), key));
            }
        } else {
            if (isRed(node.getLeft())) {
                node = rotateRight(node);
            }
            if (key.compareTo(node.getKey()) == 0 && node.getRight() == null) {
                return null; // usuwamy liść
            }
            if (node.getRight() != null) {
                if (isBlack(node.getRight()) && isBlack(node.getRight().getLeft())) {
                    node = moveRedRight(node);
                }
                if (key.compareTo(node.getKey()) == 0) {
                    // zamiana z następnikiem
                    Node<K, V> min = getMin(node.getRight());
                    node.setValue(min.getValue());
                    node = replaceKey(node, min.getKey());
                    node.setRight(deleteMin(node.getRight()));
                } else {
                    node.setRight(delete(node.getRight(), key));
                }
            }
        }
        return reorganizeTree(node);
    }

    private Node<K, V> moveRedRight(Node<K, V> node) {
        changeColors(node);
        if (isRed(node.getLeft().getLeft())) {
            node = rotateRight(node);
            changeColors(node);
        }
        return node;
    }

    private Node<K, V> getMin(Node<K, V> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    // pomocnicza metoda do podmiany klucza
    private Node<K, V> replaceKey(Node<K, V> node, K newKey) {
        node = new Node<>(newKey, node.getValue());
        return node;
    }


}
