package pl.edu.pw.ee.aisd2025zex3;

public class HashQuadraticProbing<T extends Comparable<T>> extends HashOpenAddressing<T> {
    private final int A = 1;//TODO
    private final int B = 1;//TODO

    public HashQuadraticProbing(int size) {
        super(size);
    }

    public HashQuadraticProbing() {
        super();
    }

    @Override
    public int hashFunc(int key, int i) {
        super.hashFunc(key, i);
        key = key & Integer.MAX_VALUE;
        return ((key % getSize()) + A*i + B*i*i) % getSize();
    }

}
