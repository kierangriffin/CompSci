package assign03;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class SimplePriorityQueue<E> implements PriorityQueue<E> {
    private Comparator<? super E> comparator;
    private E[] sortedArray;
    private int numElements;

    @SuppressWarnings("unchecked")
    public SimplePriorityQueue() {
        numElements = 0;  // Start with an empty array
        sortedArray = (E[]) new Object[10];  // Start with a small initial capacity
        comparator = null;
    }

    @SuppressWarnings("unchecked")
    public SimplePriorityQueue(Comparator<? super E> cmp) {
        numElements = 0;
        sortedArray = (E[]) new Object[10];
        comparator = cmp;
    }

    @Override
    public E findMax() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue is empty");
        }

        int maxIndex = findMaxIndex();
        return sortedArray[maxIndex];
    }

    @Override
    public E deleteMax() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue is empty");
        }

        int maxIndex = findMaxIndex();
        E maxElement = sortedArray[maxIndex];

        // Shift elements to the left from the max index
        System.arraycopy(sortedArray, maxIndex + 1, sortedArray, maxIndex, numElements - maxIndex - 1);

        numElements--;

        return maxElement;
    }

    @Override
    public void insert(E item) {
        // Ensure the array has enough capacity
        checkCapacity(numElements + 1);

        // Find the index where the item should be inserted
        int insertIndex = binarySearch(sortedArray, item);

        // Shift elements to the right from the insert index
        System.arraycopy(sortedArray, insertIndex, sortedArray, insertIndex + 1, numElements - insertIndex);

        // Insert the new item at the insert index
        sortedArray[insertIndex] = item;

        // Increment the size
        numElements++;
    }

    @Override
    public void insertAll(Collection<? extends E> coll) {
        for (E item : coll) {
            insert(item);
        }
    }

    @Override
    public boolean contains(E item) {
        int index = binarySearch(sortedArray, item);

        return index != -1;
    }

    @Override
    public int size() {
        return numElements;
    }

    @Override
    public boolean isEmpty() {
        return numElements == 0;
    }

    @Override
    public void clear() {
        Arrays.fill(sortedArray, 0, numElements, null);
        numElements = 0;
    }

    // Helper methods
    private int binarySearch(E[] array, E item) {
        int left = 0, right = numElements - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            int comparisonResult = compareItems(array[mid], item);

            if (comparisonResult == 0) {
                return mid;
            }

            if (comparisonResult < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;  // Return the index where the item should be inserted
    }

    private int findMaxIndex() {
        int maxIndex = 0;

        for (int i = 1; i < numElements; i++) {
            if (compareItems(sortedArray[i], sortedArray[maxIndex]) > 0) {
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    private void checkCapacity(int minCapacity) {
        if (minCapacity > sortedArray.length) {
            int newCapacity = Math.max(sortedArray.length * 2, minCapacity);
            sortedArray = Arrays.copyOf(sortedArray, newCapacity);
        }
    }

    private int compareItems(E item1, E item2) {
        if (comparator == null) {
            return ((Comparable<E>) item1).compareTo(item2);
        } else {
            return comparator.compare(item1, item2);
        }
    }
}
