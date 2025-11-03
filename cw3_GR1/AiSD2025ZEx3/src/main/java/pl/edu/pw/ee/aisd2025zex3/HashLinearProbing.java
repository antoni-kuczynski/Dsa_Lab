package pl.edu.pw.ee.aisd2025zex3;

public class HashLinearProbing<T extends Comparable<T>> extends HashOpenAddressing<T> {

    public HashLinearProbing() {
        super();
    }

    public HashLinearProbing(int size) {
        super(size);
    }

    @Override
    public int hashFunc(int key, int i) {
        super.hashFunc(key, i);
        int m = getSize();

        key = key & Integer.MAX_VALUE;

        int hash = (key % m + i) % m;

        return hash;
    }

}
