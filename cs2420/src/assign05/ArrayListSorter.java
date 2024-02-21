package assign05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ArrayListSorter {

    private static final int insertionThreshold = 5;

    public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> list) {
        mergesortRecursive(list);
    }

    /**
     * Sorts the input ArrayList using the merge sort algorithm.
     * If the size of the ArrayList is below or equal to the threshold value,
     * the algorithm switches to insertion sort for better performance.
     *
     * @param list the ArrayList to be sorted
     * @param <T>  the type of elements in the ArrayList, must implement Comparable
     */
    public static <T extends Comparable<? super T>> void mergesortRecursive(ArrayList<T> list) {
        int size = list.size();
        if (size <= 1) return; // base case

        if (size <= insertionThreshold) {
            // If the size is below or equal to the threshold, switch to insertion sort
            insertionSort(list);
            return; // return to avoid extra recursion
        }

        int middle = size / 2;

        // create left and right sublists
        ArrayList<T> leftList = new ArrayList<>(list.subList(0, middle));
        ArrayList<T> rightList = new ArrayList<>(list.subList(middle, size));

        // recursive call to sort the left and right sublists
        mergesortRecursive(leftList);
        mergesortRecursive(rightList);

        // Merge the sorted left and right sublists
        merge(leftList, rightList, list);
    }

    /**
     * Recursive generic QuickSort method, with three different ways to determine the pivot
     *
     * @param list- list to be sorted
     */
    public static <T extends Comparable<T>> void quicksort(ArrayList<T> list) {
        quicksortRecursive(list, 0, list.size()-1);

    }

    private static <T extends Comparable<T>> void quicksortRecursive(ArrayList<T> list, int start, int end) {
        if (start < end) {
            int partitionIndex = partition(list, start, end);

            // Recursively sort the sublists on either side of the partition
            quicksortRecursive(list, start, partitionIndex - 1);
            quicksortRecursive(list, partitionIndex + 1, end);
        }
    }

    /**
     * Generates an ArrayList of integers in ascending order from 1 to the specified size.
     *
     * @param size The size of the ascending list to generate.
     * @return An ArrayList of integers in ascending order.
     */
    public static ArrayList<Integer> generateAscending(int size) {
        // Create a new ArrayList to store the ascending integers
        ArrayList<Integer> ascendingList = new ArrayList<>();

        // Iterate from 1 to the specified size
        for (int i = 1; i <= size; i++) {
            // Add the current integer to the ascending list
            ascendingList.add(i);
        }

        // Return the generated ascending list
        return ascendingList;
    }

    /**
     * Generates an ArrayList of integers in permuted order.
     * It starts by creating an ascending list and then shuffles its elements.
     *
     * @param size The size of the permuted list to generate.
     * @return An ArrayList of integers in permuted order.
     */
    public static ArrayList<Integer> generatePermuted(int size) {
        // Generate an ascending list using the generateAscending method
        ArrayList<Integer> permutedList = generateAscending(size);

        // Shuffle the elements of the ascending list to create a permuted order
        Collections.shuffle(permutedList);

        // Return the generated permuted list
        return permutedList;
    }

    /**
     * Generates an ArrayList of integers in descending order from the specified size to 1.
     *
     * @param size The size of the descending list to generate.
     * @return An ArrayList of integers in descending order.
     */
    public static ArrayList<Integer> generateDescending(int size) {
        // Create a new ArrayList to store the descending integers
        ArrayList<Integer> descendingList = new ArrayList<>();

        // Iterate from the specified size to 1
        for (int i = size; i >= 1; i--) {
            // Add the current integer to the descending list
            descendingList.add(i);
        }

        // Return the generated descending list
        return descendingList;
    }

    // private helpers

    /**
     * Merges two sorted sublists into a single sorted list.
     *
     * @param leftList  the left sorted sublist
     * @param rightList the right sorted sublist
     * @param list      the main ArrayList to merge into
     * @param <T>       the type of elements in the ArrayList, must implement Comparable
     */
    private static <T extends Comparable<? super T>> void merge(ArrayList<T> leftList, ArrayList<T> rightList, ArrayList<T> list) {
        // needs to have 4 parameters two indices adn two lists, not sure why.
        int leftSize = leftList.size();
        int rightSize = rightList.size();
        int i = 0, l = 0, r = 0;

        // Compare elements from left and right sublists and merge them into the main list
        while (l < leftSize && r < rightSize) {

            if (leftList.get(l).compareTo(rightList.get(r)) < 0) {
                list.set(i, leftList.get(l));
                i++;
                l++;
            } else {
                list.set(i, rightList.get(r));
                i++;
                r++;
            }
        }

        // Copy any remaining elements from the left sublist to the main list
        while (l < leftSize) {
            list.set(i, leftList.get(l));
            i++;
            l++;
        }

        // Copy any remaining elements from the right sublist to the main list
        while (r < rightSize) {
            list.set(i, rightList.get(r));
            i++;
            r++;
        }
    }

    /**
     * Sorts the input ArrayList using the insertion sort algorithm.
     *
     * @param list the ArrayList to be sorted
     * @param <T>  the type of elements in the ArrayList, must implement Comparable
     */
    private static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> list) {
        int size = list.size();

        // Iterate over the elements starting from the second element (index 1)
        for (int i = 1; i < size; i++) {
            // Store the current element to be compared and inserted in its correct position
            T key = list.get(i);

            // Initialize the index for comparing elements to the left of the current element
            int j = i - 1;

            // Compare the current element with elements to its left and shift them if necessary
            // Move elements greater than the key to the right to make space for the key
            while (j >= 0 && list.get(j).compareTo(key) > 0) {
                list.set(j + 1, list.get(j)); // Shift the element to the right
                j--; // Move to the next element to the left
            }

            // Insert the key into its correct sorted position
            list.set(j + 1, key);
        }
    }

    // A utility function to swap two elements
    private static <T> void swap(ArrayList<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    /**
     * Find Pivot 1 returns the index of the middle element for the array that is passed.
     * If the List is of odd length, the index left of the middle is returned.
     *
     * @param list- Array passed
     * @return nt- index for pivot
     */
    private static <T> int findPivot1(ArrayList<T> list) {

        return list.size() / 2;
    }

    /**
     * This method returns a pivot index based on an approximate median from First Middle and Last element of the list passed.
     *
     * @param list- list given
     * @return int - pivot index
     */
    private static <T extends Comparable<? super T>> int findPivot2(ArrayList<T> list) {
        // get three elements and add to array list
        ArrayList<T> arr = new ArrayList<T>();
        T first = list.getFirst();
        T middle = list.get(list.size() / 2);
        T last = list.getLast();
        arr.add(first);
        arr.add(middle);
        arr.add(last);
        insertionSort(arr);
        return list.indexOf(arr.get(1));

    }


    /**
     *
     * @param list - the list to find the pivot of
     * @return - the index of the element to pivot around
     */
    public static <T> int findPivot3(ArrayList<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Array should not be null or empty");
        }

        Random rand = new Random();
        return rand.nextInt(list.size());


    }

    public static <T extends Comparable<? super T>> int partition(ArrayList<T> list, int start, int end) {
        int pivotIndex = findPivot1(list); // using middle for now
        T pivot = list.get(pivotIndex);

        int i = start;
        int j = end;

        swap(list, pivotIndex, j);

        while (i <= j) {
            // Find element on the left side that is greater than or equal to the pivot
            while (list.get(i).compareTo(pivot) < 0) {
                i++;
            }

            // Find element on the right side that is smaller than or equal to the pivot
            while (list.get(j).compareTo(pivot) > 0) {
                j--;
            }

            // Swap the elements found
            if (i <= j) {
                swap(list, i, j);
                i++;
                j--;
            }
        }

        swap(list, i, pivotIndex);

        // The index 'i' is the partition point
        return i;
    }



}