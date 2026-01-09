package pl.edu.pw.ee.struct.queue;

public class PriorityQueue<T extends Comparable<T>> {
    private final MinHeap<T> heap = new MinHeap<>();

    public void offer(T element) {
        heap.insert(element);
    }

    public T poll() {
        return heap.extractMin();
    }

    public T peek() {
        return heap.peek();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    public void clear() {
        while (!heap.isEmpty()) {
            heap.extractMin();
        }
    }
}
