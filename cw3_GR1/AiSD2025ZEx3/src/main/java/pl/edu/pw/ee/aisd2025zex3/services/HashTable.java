package pl.edu.pw.ee.aisd2025zex3.services;

public interface HashTable<T extends Comparable<T>> {

    void put(T newElem);

    T get(T elem);

    void delete(T elem);

}
