package pl.edu.pw.ee.aisd2025zex2.multiplicative;

import pl.edu.pw.ee.aisd2025zex2.HashListChainingMultiplicative;

public class HashListChainingMultiplicativeHashingA3<T extends Comparable<T>> extends HashListChainingMultiplicative<T> {

    public HashListChainingMultiplicativeHashingA3() {
        super();
    }

    public HashListChainingMultiplicativeHashingA3(int size) {
        super(size);
    }

    @Override
    public double getAConstant() {
        return A3;
    }
}
