package pl.edu.pw.ee.aisd2025zex7.shortestpaths.singlesource.dijkstra.utils;

import static java.lang.String.format;

public class IndexedMinPQ {

    private final int initHeapVal = -1;

    private final int maxSize;
    private final int[] heap;
    private final int[] invHeap;
    private final int[] priorities;

    private int nItems;

    public IndexedMinPQ(int maxSize) {
        validateMaxSize(maxSize);

        this.maxSize = maxSize;
        nItems = 0;
        heap = new int[maxSize + 1];
        invHeap = createInvHeap(maxSize + 1);
        priorities = new int[maxSize + 1];
    }

    public void insert(int verticeId, int priority) {
        validateVerticeId(verticeId);
        throwIfAlreadyExist(verticeId);

        nItems++;
        heap[nItems] = verticeId;
        invHeap[verticeId] = nItems;
        priorities[verticeId] = priority;
        heapUp(nItems);
    }

    public void updatePriority(int verticeId, int newPriority) {
        validateVerticeId(verticeId);

        throwIfNotExist(verticeId);

        priorities[verticeId] = newPriority;

        heapUp(invHeap[verticeId]);
        heapDown(invHeap[verticeId]);
    }

    public boolean isEmpty() {
        return nItems == 0;
    }

    public boolean contains(int verticeId) {
        validateVerticeId(verticeId);

        boolean verticeInQueue = (invHeap[verticeId] != initHeapVal);

        return verticeInQueue;
    }

    public int poll() {
        if (nItems == 0) {
            throw new IllegalStateException("Cannot poll an item from an empty queue!");
        }

        int verticeId = heap[1];
        swap(1, nItems--);
        heapDown(1);
        invHeap[verticeId] = initHeapVal;

        return verticeId;
    }

    private void validateMaxSize(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("Maximum size must be greater than 0!");
        }
    }

    private int[] createInvHeap(int size) {
        int[] initialInvHeap = new int[size];

        for (int i = 0; i < size; i++) {
            initialInvHeap[i] = initHeapVal;
        }

        return initialInvHeap;
    }

    private void validateVerticeId(int verticeId) {
        if (verticeId < 0) {
            String errMessage = format("Vertice id (%d) cannot be negative!", verticeId);
            throw new IllegalArgumentException(errMessage);
        }

        if (verticeId >= maxSize) {
            String errMessage = format("Vertice id (%d) should be lower than maxSize (%d)!", verticeId, maxSize);
            throw new IllegalArgumentException(errMessage);
        }
    }

    private void throwIfAlreadyExist(int verticeId) {
        if (contains(verticeId)) {
            String errMessage = format("Vertice (%d) is already in the queue!", verticeId);
            throw new IllegalArgumentException(errMessage);
        }
    }

    private void throwIfNotExist(int verticeId) {
        if (!contains(verticeId)) {
            String errMessage = format("Cannot update a vertice (%d) that is not currently in the queue!", verticeId);
            throw new IllegalArgumentException(errMessage);
        }
    }

    private void heapUp(int childId) {
        int parentId = childId / 2;

        while (childId > 1 && isGreaterPriority(parentId, childId)) {
            swap(childId, parentId);
            childId = parentId;
            parentId /= 2;
        }
    }

    private void heapDown(int parentId) {
        int childId = 2 * parentId;

        while (childId <= nItems) {

            if (childId < nItems && isGreaterPriority(childId, childId + 1)) {
                childId++;
            }
            if (!isGreaterPriority(parentId, childId)) {
                break;
            }
            swap(parentId, childId);
            parentId = childId;
            childId = 2 * parentId;
        }
    }

    private boolean isGreaterPriority(int heapFirstIndex, int heapSecondIndex) {
        int firstVerticeId = heap[heapFirstIndex];
        int secondVerticeId = heap[heapSecondIndex];

        return priorities[firstVerticeId] > priorities[secondVerticeId];
    }

    private void swap(int firstId, int secondId) {
        if (firstId != secondId) {
            int firstVal = heap[firstId];
            heap[firstId] = heap[secondId];
            heap[secondId] = firstVal;

            invHeap[heap[firstId]] = firstId;
            invHeap[heap[secondId]] = secondId;
        }
    }

}
