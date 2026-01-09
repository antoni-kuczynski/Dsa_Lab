package pl.edu.pw.ee.aisd2025zex2.services;

public interface HashTable<T extends Comparable<T>> {

    void add(T value);

    T get(T value);

    void delete(T value);

    double countLoadFactor();
}
