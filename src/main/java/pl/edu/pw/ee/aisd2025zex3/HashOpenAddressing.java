package pl.edu.pw.ee.aisd2025zex3;

import pl.edu.pw.ee.aisd2025zex3.services.DeletedObject;
import pl.edu.pw.ee.aisd2025zex3.services.HashTable;

public abstract class HashOpenAddressing<T extends Comparable<T>> implements HashTable<T> {

    private final T nil = null;
    private final T deleted = (T) DeletedObject.DELETED;
    private int size;
    private int nElems;
    private T[] hashElems;
    private final double correctLoadFactor;
    private int amountOfPutCalls = 0;
    private int amountOfHashCalls = 0;
    private int amountOfDoubleResizeCalls = 0;


    HashOpenAddressing() {
        this(2039); // initial size as random prime number
    }

    HashOpenAddressing(int size) {
        validateHashInitSize(size);

        this.size = size;
        this.hashElems = createTable(this.size);
        this.correctLoadFactor = 0.75;
    }

    @Override
    public void put(T newElem) {
        amountOfPutCalls++;
        validateInputElem(newElem);
        resizeIfNeeded();

        int key = newElem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while (hashElems[hashId] != nil && hashElems[hashId] != deleted) { //dodano wstawianie do elementów o wartości deleted
            if (i + 1 == size) {
                doubleResize();
                i = -1;
            }
            i = (i + 1) % size;
            hashId = hashFunc(key, i);
        }

        hashElems[hashId] = newElem;
        nElems++;
    }

    @Override
    public T get(T elem) {
        validateInputElem(elem);
        int key = elem.hashCode();
        int i = 0;
        int hashIndex = hashFunc(key, i);

        //W tej pętli nie było warunku "hashElems[hashIndex] != deleted"
//        while (hashElems[hashIndex] != nil) {
        while (hashElems[hashIndex] != nil || hashElems[hashIndex] != deleted) {
            T current = hashElems[hashIndex];
            if (current != deleted && current.equals(elem)) {
                return current;
            }
            i++;
            hashIndex = hashFunc(key, i);
        }
        return nil;
    }

    @Override
    public void delete(T elem) {
        validateInputElem(elem);
        int key = elem.hashCode();
        int i = 0;
        int hashIndex = hashFunc(key, i);

        while (hashElems[hashIndex] != nil) {
            T searched = hashElems[hashIndex];
            if(searched != deleted && searched.equals(elem)) {
                hashElems[hashIndex] = deleted;
                nElems--;
                return;
            }
            i++;
            hashIndex = hashFunc(key, i);
        }
    }

    private void validateHashInitSize(int initialSize) {
        if (initialSize < 1) {
            throw new IllegalArgumentException("Initial size of hash table cannot be lower than 1!");
        }
    }

    private void validateInputElem(T newElem) {
        if (newElem == null) {
            throw new IllegalArgumentException("Input elem cannot be null!");
        }
    }

    public int hashFunc(int key, int i){
        amountOfHashCalls++;
        return 0;
    }

    int getSize() {
        return size;
    }

    private T[] createTable(int size) {
        return (T[]) new Comparable[size];
    }

    private void resizeIfNeeded() {
        double loadFactor = countLoadFactor();

        if (loadFactor >= correctLoadFactor) {
            doubleResize();
        }
    }

    public double countLoadFactor() {
        return (double) nElems / size;
    }

    private void doubleResize() {
        amountOfDoubleResizeCalls++;
        this.size *= 2;

        T[] oldElems = hashElems;
        hashElems = createTable(size);
        nElems = 0;

        T currentElem;
        for (int i = 0; i < oldElems.length; i++) {
            currentElem = oldElems[i];

            //dodano sprawdzanie czy element jest deleted
            if (currentElem != nil && currentElem != deleted) {
                put(currentElem);
                oldElems[i] = nil;
            }
        }
    }

    public int getAmountOfDoubleResizeCalls() {
        return amountOfDoubleResizeCalls;
    }

    public int getAmountOfHashCalls() {
        return amountOfHashCalls;
    }

    public int getAmountOfPutCalls() {
        return amountOfPutCalls;
    }
}
