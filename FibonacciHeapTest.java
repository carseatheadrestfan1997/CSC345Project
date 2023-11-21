import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciHeapTest {

    FibonacciHeap<Integer> h;

    @BeforeEach
    void setup() {
        h = new FibonacciHeap<>();
    }

    @Test
    void insert() {
        h.insert(1);
        assertEquals(1, (int) h.find_min().getVal());
        h.insert(2);
        assertEquals(1, (int) h.find_min().getVal());
        h.insert(0);
        assertEquals(0, (int) h.find_min().getVal());
        for (int i = 10; i < 1000; i++) {
            h.insert(i);
        }
        assertEquals(0, (int) h.find_min().getVal());
    }

    @Test
    void size() {
        assertEquals(0, h.size());
        for (int i = 0; i < 10; i++) {
            h.insert(i);
        }
        assertEquals(10, h.size());
        for (int i = 0; i < 5; i++) {
            h.delete_min();
        }
        assertEquals(5, h.size());
        for (int i = 0; i < 5; i++) {
            h.delete_min();
        }
        assertEquals(0, h.size());
    }

    @Test
    void isEmpty() {
        assertTrue(h.isEmpty());
        h.insert(10);
        assertFalse(h.isEmpty());
        h.delete_min();
        assertTrue(h.isEmpty());
    }

    @Test
    void find_min() {
        NoSuchElementException e = assertThrows(NoSuchElementException.class, h::find_min);
        assertEquals(e.getMessage(), "empty heap");
        h.insert(10);
        assertEquals(h.find_min().getVal(), 10);
        for (int i = 11; i < 100; i++) {
            h.insert(i);
        }
        assertEquals(h.find_min().getVal(), 10);
    }

    @Test
    void delete_min() {
        for (int i = 0; i < 100; i++) {
            h.insert(i);
        }
        for (int i = 0; i < 100; i++) {
            int t = h.delete_min().getVal();
            assertEquals(i, t);
        }
    }

    @Test
    void decrease_key() {
        h.insert(10);
        h.decrease_key(h.find_min(), 5);
        assertEquals(h.find_min().getVal(), 5);
    }

    @Test
    void merge() {
        FibonacciHeap<Integer> h2 = new FibonacciHeap<>();
        for (int i = 0; i < 15; i++) {
            h.insert(i);
        }
        for (int i = 10; i < 25; i++) {
            h2.insert(i);
        }

        FibonacciHeap<Integer> h3 = new FibonacciHeap<>();
        h3 = h3.merge(h, h2);

        assertEquals(h3.find_min().getVal(), 0);
        assertEquals(h3.size(), 30);

    }
}