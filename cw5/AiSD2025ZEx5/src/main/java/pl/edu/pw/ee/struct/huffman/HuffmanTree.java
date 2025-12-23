package pl.edu.pw.ee.struct.huffman;

import pl.edu.pw.ee.binary.io.BitReader;
import pl.edu.pw.ee.binary.misc.BinaryConverter;

import java.io.*;
import java.util.ArrayList;
import java.util.function.Consumer;

public class HuffmanTree {
    private HuffmanNode root;

    private static final char INTERNAL_NODE_CODE = '0';
    private static final char KEY_NODE_CODE = '1';
    private static final String LEFT_NODE_CODE = "0";
    private static final String RIGHT_NODE_CODE = "1";

    public static HuffmanTree buildFromInOrderInFile(BitReader reader, int amountOfStoredKeyLengthBits) throws IOException {
        HuffmanTree tree = new HuffmanTree();
        tree.root = getRootFromFileReader(reader, amountOfStoredKeyLengthBits);
        tree.assignCodes();
        return tree;
    }

    public static HuffmanTree buildFromPriorityQueue(HuffmanPriorityQueue queue) {
        while (queue.size() > 1) {
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll();

            HuffmanNode node = HuffmanNode.newEmptyNode(left.getFrequency() + right.getFrequency(), null);
            node.setLeft(left);
            node.setRight(right);
            queue.offer(node);
        }

        HuffmanTree tree = new HuffmanTree();
        tree.root = queue.poll();
        tree.assignCodes();
        return tree;
    }

    private static HuffmanNode getRootFromFileReader(BitReader reader, int amountOfStoredKeyLengthBits) throws IOException {
        int flag = reader.readBit();

        if (flag == HuffmanNode.FLAG_KEY_NODE) {
            int nodesChainLength;
            if (amountOfStoredKeyLengthBits > 0) {
                nodesChainLength = reader.readBits(amountOfStoredKeyLengthBits);
            } else {
                nodesChainLength = 1;
            }

            byte[] key = new byte[nodesChainLength];

            for (int i = 0; i < nodesChainLength; i++) {
                int b = 0;
                for (int bit = 7; bit >= 0; bit--) {
                    int v = reader.readBit();
                    if (v == -1) {
                        throw new EOFException("Unexpected EOF while reading leaf key bits");
                    }
                    b |= (v & 0x01) << bit;
                }
                key[i] = (byte) (b & 0xFF);
            }

            return HuffmanNode.newKeyNode(key, 0, "");
        }

        if (flag == HuffmanNode.FLAG_INTERNAL_NODE) {
            HuffmanNode left = getRootFromFileReader(reader, amountOfStoredKeyLengthBits);
            HuffmanNode right = getRootFromFileReader(reader, amountOfStoredKeyLengthBits);

            HuffmanNode node = HuffmanNode.newEmptyNode(0, "");
            node.setLeft(left);
            node.setRight(right);
            return node;
        }

        throw new IllegalStateException("Invalid bit: " + flag);
    }

    private void assignCodes() {
        if (root.isLeaf()) {
            root.setCode(LEFT_NODE_CODE);
            return;
        }

        assignCodes(root, "");
    }

    private void assignCodes(HuffmanNode node, String code) {
        if (node == null)
            return;

        node.setCode(code);

        assignCodes(node.getLeft(), code + LEFT_NODE_CODE);
        assignCodes(node.getRight(), code + RIGHT_NODE_CODE);
    }

    public void inorderWithFunction(Consumer<HuffmanNode> action) {
        ArrayList<HuffmanNode> stack = new ArrayList<>();
        HuffmanNode current = root;

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

    private void preOrderWithFunction(Consumer<HuffmanNode> action) {
        if (root == null)
            return;

        ArrayList<HuffmanNode> stack = new ArrayList<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            HuffmanNode node = stack.remove(stack.size() - 1);
            action.accept(node);

            if (node.getRight() != null)
                stack.add(node.getRight());

            if (node.getLeft() != null)
                stack.add(node.getLeft());
        }
    }

    public String asPreOrderString(int amountOfStoredKeyLengthBits) {
        StringBuilder s = new StringBuilder();

        Consumer<HuffmanNode> f = node -> {
            if (node.getLeft() != null || node.getRight() != null) {
                s.append(INTERNAL_NODE_CODE);
            } else {
                s.append(KEY_NODE_CODE);

                byte[] key = node.getKey();
                if (amountOfStoredKeyLengthBits > 0) {
                    s.append(BinaryConverter.intToBinary(key.length, amountOfStoredKeyLengthBits));
                }
                s.append(BinaryConverter.byteArrayTo8BitBinary(key));
            }
        };

        preOrderWithFunction(f);
        return s.toString();
    }

    public void printTree() {
        root.printNode("", true);
    }

    public HuffmanNode getRoot() {
        return root;
    }
}
