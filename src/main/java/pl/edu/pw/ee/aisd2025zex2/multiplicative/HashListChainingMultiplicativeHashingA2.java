package pl.edu.pw.ee.aisd2025zex2.multiplicative;

import pl.edu.pw.ee.aisd2025zex2.HashListChainingMultiplicative;

public class HashListChainingMultiplicativeHashingA2<T extends Comparable<T>> extends HashListChainingMultiplicative<T> {

    public HashListChainingMultiplicativeHashingA2() {
        super();
    }

    public HashListChainingMultiplicativeHashingA2(int size) {
        super(size);
    }

    @Override
    public double getAConstant() {
        return A2;
    }
}
