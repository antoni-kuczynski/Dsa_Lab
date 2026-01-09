package pl.edu.pw.ee;

import pl.edu.pw.ee.struct.queue.PriorityQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PriorityQueueTest {

    private PriorityQueue<String> queue;

    @BeforeEach
    void setUp() {
        queue = new PriorityQueue<>();
        queue.offer("ala");
        queue.offer("kot");
        queue.offer("pies");
        queue.offer("baran");
    }

    @Test
    public void peekReturnsMin() {
        assertEquals("ala", queue.peek());
    }

    @Test
    public void pollWorks() {
        String min = queue.poll();
        assertEquals("ala", min);
    }

    @Test
    public void offerWorks() {
        queue.offer("000");
        assertEquals("000", queue.peek());
    }

    @Test
    public void pollAfterOfferWorks() {
        String min = queue.poll();
        queue.offer("a");
        assertEquals("ala", min);
        assertEquals("a", queue.poll());
    }

    @Test
    public void clearWorks() {
        queue.clear();
        assertTrue(queue.isEmpty());
        assertNull(queue.peek());
    }

    @Test
    public void sizeWorks() {
        assertEquals(4, queue.size());
        queue.poll();
        assertEquals(3, queue.size());
    }
}
