package pl.edu.pw.ee.struct.queue;

import java.util.ArrayList;
import java.util.List;

public class MinHeap<T extends Comparable<T>> {
    private final List<T> data = new ArrayList<>();

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public T peek() {
        if (data.isEmpty()) return null;
        return data.get(0);
    }

    public int parent(int i) {
        if (i <= 0 || i >= size()) throw new IndexOutOfBoundsException();
        return (i - 1) / 2;
    }

    public int left(int i) {
        return 2 * i + 1;
    }

    public int right(int i) {
        return 2 * i + 2;
    }

    public void insert(T value) {
        data.add(value);
        heapifyUp(size() - 1);
    }

    public T extractMin() {
        if (data.isEmpty()) return null;
        T min = data.get(0);
        T last = data.remove(size() - 1);
        if (!data.isEmpty()) {
            data.set(0, last);
            heapifyDown(0);
        }
        return min;
    }

    private void heapifyUp(int i) {
        while (i > 0) {
            int p = parent(i);
            if (data.get(i).compareTo(data.get(p)) < 0) {
                swap(i, p);
                i = p;
            } else {
                break;
            }
        }
    }

    private void heapifyDown(int i) {
        while (true) {
            int l = left(i);
            int r = right(i);
            int smallest = i;

            if (l < size() && data.get(l).compareTo(data.get(smallest)) < 0)
                smallest = l;

            if (r < size() && data.get(r).compareTo(data.get(smallest)) < 0)
                smallest = r;

            if (smallest != i) {
                swap(i, smallest);
                i = smallest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        T tmp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, tmp);
    }
}
