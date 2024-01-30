package assign03;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

class SimplePriorityQueueTest {

    @Test
    void findMaxEmptyQueue() {
        SimplePriorityQueue<Integer> queue = new SimplePriorityQueue<>();
        assertThrows(NoSuchElementException.class, queue::findMax);
    }

    @Test
    void findMax() {
        SimplePriorityQueue<Integer> queue = new SimplePriorityQueue<>();
        queue.insert(5);
        queue.insert(10);
        queue.insert(7);
        assertEquals(10, queue.findMax());
    }

    @Test
    void deleteMaxEmptyQueue() {
        SimplePriorityQueue<Integer> queue = new SimplePriorityQueue<>();
        assertThrows(NoSuchElementException.class, queue::deleteMax);
    }

    @Test
    void deleteMax() {
        SimplePriorityQueue<Integer> queue = new SimplePriorityQueue<>();
        queue.insert(5);
        queue.insert(10);
        queue.insert(7);
        assertEquals(10, queue.deleteMax());
        assertEquals(7, queue.findMax());
    }

    @Test
    void insert() {
        SimplePriorityQueue<Integer> queue = new SimplePriorityQueue<>();
        queue.insert(5);
        queue.insert(10);
        queue.insert(7);
        assertEquals(5, queue.findMax());
    }

    @Test
    void insertAll() {
        SimplePriorityQueue<Integer> queue = new SimplePriorityQueue<>();
        Collection<Integer> elements = Arrays.asList(5, 10, 7);
        queue.insertAll(elements);
        assertEquals(10, queue.findMax());
    }

    @Test
    void contains() {
        SimplePriorityQueue<Integer> queue = new SimplePriorityQueue<>();
        queue.insert(5);
        queue.insert(10);
        queue.insert(7);
        assertTrue(queue.contains(7));
        assertFalse(queue.contains(15));
    }

    @Test
    void size() {
        SimplePriorityQueue<Integer> queue = new SimplePriorityQueue<>();
        queue.insert(5);
        queue.insert(10);
        queue.insert(7);
        assertEquals(3, queue.size());
    }

    @Test
    void isEmpty() {
        SimplePriorityQueue<Integer> queue = new SimplePriorityQueue<>();
        assertTrue(queue.isEmpty());
        queue.insert(5);
        assertFalse(queue.isEmpty());
    }

    @Test
    void clear() {
        SimplePriorityQueue<Integer> queue = new SimplePriorityQueue<>();
        queue.insert(5);
        queue.insert(10);
        queue.clear();
        assertTrue(queue.isEmpty());
    }

}
