package assign03;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * A simple implementation of a priority queue using an array-based sorted structure.
 *
 * @param <E> the type of elements in the priority queue
 */
public class SimplePriorityQueue<E> implements PriorityQueue<E> {
    private Comparator<? super E> comparator;
    public E[] sortedArray;
    private int numElements;

    /**
     * Constructs an empty priority queue with a natural ordering of elements.
     */
    @SuppressWarnings("unchecked")
    public SimplePriorityQueue() {
        numElements = 0;
        sortedArray = (E[]) new Object[10];
    }

    /**
     * Constructs an empty priority queue with the specified comparator.
     *
     * @param cmp the comparator to determine the order of elements
     */
    @SuppressWarnings("unchecked")
    public SimplePriorityQueue(Comparator<? super E> cmp) {
        numElements = 0;
        sortedArray = (E[]) new Object[10];
        comparator = cmp;
    }

    /**
     * Retrieves, but does not remove, the maximum element in this priority queue.
     *
     * @return the maximum element
     * @throws NoSuchElementException if the priority queue is empty
     */
    @Override
    public E findMax() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue is empty");
        }

        return sortedArray[numElements - 1];
    }

    /**
     * Retrieves and removes the maximum element in this priority queue.
     *
     * @return the maximum element
     * @throws NoSuchElementException if the priority queue is empty
     */
    @Override
    public E deleteMax() throws NoSuchElementException {
       E maxElement = findMax();
        -- numElements;

        return maxElement;
    }

    /**
     * Inserts the specified element into this priority queue.
     *
     * @param item the element to insert
     */
    @Override
    public void insert(E item) {
        checkCapacity(numElements + 1);
        int insertIndex = binarySearch(item, true);

        System.arraycopy(sortedArray, insertIndex, sortedArray, insertIndex + 1, numElements - insertIndex);

        sortedArray[insertIndex] = item;

        numElements++;
    }

    /**
     * Inserts the specified elements into this priority queue.
     *
     * @param coll the collection of elements to insert
     */
    @Override
    public void insertAll(Collection<? extends E> coll) {
        for (E item : coll) {
            insert(item);
        }
    }

    /**
     * Indicates whether this priority queue contains the specified element.
     *
     * @param item the element to be checked for containment in this priority queue
     * @return true if the priority queue contains the element, false otherwise
     */
    @Override
    public boolean contains(E item) {
        return binarySearch(item, false) != -1;
    }

    /**
     * Returns the number of elements in this priority queue.
     *
     * @return the number of elements in this priority queue
     */
    @Override
    public int size() {
        return numElements;
    }

    /**
     * Returns true if this priority queue contains no elements, false otherwise.
     *
     * @return true if this priority queue contains no elements, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return numElements == 0;
    }

    /**
     * Removes all the elements from this priority queue. The queue will be
     * empty when this call returns.
     */
    @Override
    public void clear() {
        Arrays.fill(sortedArray, 0, numElements, null);
        numElements = 0;
    }

    /**
     * Performs binary search on the specified sorted array to find the index of the given item or the position
     * where the item should be inserted to maintain the array's sorted order.
     *
     * @param item     the item to be searched or inserted
     * @param forInsert true if the search is for insertion, false for regular search
     * @return The index of the item if found; otherwise, the index where the item should be inserted.
     */
    private int binarySearch(E item, boolean forInsert) {
        int left = 0, right = numElements - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            int comparisonResult = compareItems(sortedArray[mid], item);

            if (comparisonResult == 0) {
                return mid;
            }

            if (comparisonResult < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return (forInsert) ? left : -1;
    }

    /**
     * Checks and adjusts the capacity of the internal sorted array to accommodate at least the specified minimum capacity.
     *
     * @param minCapacity The minimum capacity that should be ensured.
     */
    private void checkCapacity(int minCapacity) {
        if (minCapacity > sortedArray.length) {

            sortedArray = Arrays.copyOf(sortedArray, 2 * sortedArray.length);
        }
    }

    /**
     * Compares two items using either the natural ordering or the provided comparator.
     *
     * @param item1 The first item to be compared.
     * @param item2 The second item to be compared.
     * @return A negative integer, zero, or a positive integer as the first item is less than, equal to, or greater than the second.
     */
    @SuppressWarnings("unchecked")
    private int compareItems(E item1, E item2) {
        return (comparator == null) ? ((Comparable<E>) item1).compareTo(item2) : comparator.compare(item1, item2);
    }
}
