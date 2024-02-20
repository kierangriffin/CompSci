package assign05;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayListSorter {

    private static final int insertionThreshold = 5;

    /**
     * Sorts the input ArrayList using the merge sort algorithm.
     * If the size of the ArrayList is below or equal to the threshold value,
     * the algorithm switches to insertion sort for better performance.
     *
     * @param list the ArrayList to be sorted
     * @param <T> the type of elements in the ArrayList, must implement Comparable
     */
    public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> list) {
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
        mergesort(leftList);
        mergesort(rightList);

        // Merge the sorted left and right sublists
        merge(leftList, rightList, list);
    }

    public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> list) {

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
     * @param leftList the left sorted sublist
     * @param rightList the right sorted sublist
     * @param list the main ArrayList to merge into
     * @param <T> the type of elements in the ArrayList, must implement Comparable
     */
    private static <T extends Comparable<? super T>> void merge(ArrayList<T> leftList, ArrayList<T> rightList, ArrayList<T> list) {
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
     * @param arr the ArrayList to be sorted
     * @param <T> the type of elements in the ArrayList, must implement Comparable
     */
    private static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> arr) {
        int size = arr.size();

        for (int i = 1; i < size; i++) {
            T key = arr.get(i);
            int j = i - 1;

            while (j >= 0 && arr.get(j).compareTo(key) > 0) {
                arr.set(j + 1, arr.get(j));
                j--;
            }
            arr.set(j + 1, key);
        }
    }


}

