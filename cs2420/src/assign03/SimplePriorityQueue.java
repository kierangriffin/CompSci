package assign03;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class SimplePriorityQueue<E> implements PriorityQueue {

    private static final Class<?> E = Object.class;
    private Comparator<? super E> comparator;
    private E[] sortedArray;
    private int size;

    public SimplePriorityQueue() {
        size = 10;
        sortedArray = (E[]) Array.newInstance(E, size);
        comparator = null;
    }

    public SimplePriorityQueue(Comparator<? super E> cmp) {
        size = 10;
        sortedArray = (E[]) Array.newInstance(E, size);
        comparator = cmp;
    }

    /**
     * Retrieves, but does not remove, the maximum element in this priority
     * queue.
     *
     * @return the maximum element
     * @throws NoSuchElementException if the priority queue is empty
     */
    @Override
    public Object findMax() throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException("Priority queue is empty");
        }

        int maxIndex = findMaxIndex();
        return sortedArray[maxIndex];
    }

    /**
     * Retrieves and removes the maximum element in this priority queue.
     *
     * @return the maximum element
     * @throws NoSuchElementException if the priority queue is empty
     */
    @Override
    public Object deleteMax() throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException("Priority queue is empty");
        }

        int maxIndex = findMaxIndex();
        E maxElement = sortedArray[maxIndex];

        // Shift elements to remove the maximum element
        for (int i = maxIndex; i < size - 1; i++) {
            sortedArray[i] = sortedArray[i + 1];
        }

        // Update the size
        size--;

        return maxElement;
    }

    // Private method to find the index of the maximum element
    private int findMaxIndex() {
        int maxIndex = 0;

        for (int i = 1; i < size; i++) {
            if (comparator == null) {
                if (((Comparable<E>) sortedArray[i]).compareTo(sortedArray[maxIndex]) > 0) {
                    maxIndex = i;
                }
            } else {
                if (comparator.compare(sortedArray[i], sortedArray[maxIndex]) > 0) {
                    maxIndex = i;
                }
            }
        }

        return maxIndex;
    }


    /**
     * Inserts the specified element into this priority queue.
     *
     * @param item - the element to insert
     */
    @Override
    public void insert(Object item) {

    }

    /**
     * Inserts the specified elements into this priority queue.
     *
     * @param coll - the collection of elements to insert
     */
    @Override
    public void insertAll(Collection coll) {

    }

    /**
     * Indicates whether this priority queue contains the specified element.
     *
     * @param item - the element to be checked for containment in this priority queue
     */
    @Override
    public boolean contains(Object item) {
        int index = binarySearch(sortedArray, item);

        return index != -1;
    }

    private int binarySearch(E[] array, Object item) {
        int left = 0, right = array.length - 1;

        while (left <= right) {
            int mid = right / 2;

            int comparisonResult = Integer.parseInt(array[mid].toString());

            if (comparisonResult == 0) {
                return mid;
            }

            // if target is greater ignore left half
            if (comparisonResult < 0) {
                left = mid + 1;
            }

            // if target is smaller ignore right half
            else {
                right = mid - 1;
            }
        }

        return -1;
    }

    /**
     * @return the number of elements in this priority queue
     */
    @Override
    public int size() {

        return sortedArray.length - 1;
    }

    /**
     * @return true if this priority queue contains no elements, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes all the elements from this priority queue. The queue will be
     * empty when this call returns.
     */
    @Override
    public void clear() {

    }

//    returns the index where the target should be
//
//    private int binarySearch(E needle) {
//
//    }

//    contains
//       compare target w all index
//    insert
//       that's where to put the new value


}
