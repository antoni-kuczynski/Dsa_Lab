package pl.edu.pw.ee.aisd2025zex3;

public class HashDoubleHashing<T extends Comparable<T>> extends HashOpenAddressing<T> {

    private int hashingFunction1(int key) {
        return key % getSize();
    }

    private int hashingFunction2(int key) {
        return 1 + (key % (getSize() - 1));
    }

    public HashDoubleHashing(int size) {
        super(size);
    }

    public HashDoubleHashing() {
        super();
    }

    @Override
    public int hashFunc(int key, int i) {
        super.hashFunc(key, i);
        key = key & Integer.MAX_VALUE;

        long f1 = hashingFunction1(key);
        long f2TimesI = (long) i * hashingFunction2(key);

        return (int) ((f1 + f2TimesI ) % getSize());
    }

}
