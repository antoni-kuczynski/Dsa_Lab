package pl.edu.pw.ee.struct.huffman;

import pl.edu.pw.ee.binary.io.BitWriter;

import java.util.ArrayList;
import java.util.Arrays;

public class HuffmanNode implements Comparable<HuffmanNode> {

    private HuffmanNode left;
    private HuffmanNode right;
    private byte[] key;
    private long frequency;
    private String code;

    public static int FLAG_INTERNAL_NODE = 0;
    public static int FLAG_KEY_NODE = 1;

    public static HuffmanNode newEmptyNode(long frequency, String code) {
        return new HuffmanNode(null, frequency, code);
    }

    public static HuffmanNode newKeyNode(byte[] key, long frequency, String code) {
        return new HuffmanNode(key, frequency, code);
    }


    private HuffmanNode(byte[] key, long frequency, String code) {
        this.key = key;
        this.frequency = frequency;
        this.code = code;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }

    public byte[] getKey() {
        return key;
    }

    public long getFrequency() {
        return frequency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isLeaf() {
        return this.right == null && this.left == null;
    }

    @Override
    public int compareTo(HuffmanNode o) {
        if (frequency > o.frequency) {
            return 1;
        } else if (frequency < o.frequency) {
            return -1;
        }

        if (key != null && o.key != null)
            return BitWriter.compareByteArrays(key, o.key);

        if (key == null && o.key != null)
            return 1;

        if (key != null)
            return -1;

        return 0;
    }

    protected void printNode(String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "└── " : "├── ")
                + (this.getKey() == null ? "*" : Arrays.toString(this.getKey()))
                + " (" + this.getFrequency() + ")"
                + " [w=" + this.getCode() + "]");

        ArrayList<HuffmanNode> children = new ArrayList<>();
        if (this.getLeft() != null) children.add(this.getLeft());
        if (this.getRight() != null) children.add(this.getRight());

        for (int i = 0; i < children.size() - 1; i++) {
            children.get(i).printNode(prefix + (isTail ? "    " : "│   "), false);
        }

        if (children.size() > 0) {
            children.get(children.size() - 1).printNode(prefix + (isTail ? "    " : "│   "), true);
        }
    }
}
