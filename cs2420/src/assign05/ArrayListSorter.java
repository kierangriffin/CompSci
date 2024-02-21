package assign05;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayListSorter {

    private static final int INSERTION_THRESHOLD = 20;

    /**
     * Driver method for mergesort
     *
     * @param list - list to be sorted
     */
    public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> list) {
        ArrayList<T> temp = new ArrayList<>(list.size());
        for (int i = 0; i < list.size(); i++) {
            temp.add(null); // Pre-allocate space for merging
        }
        mergesort(list, temp, 0, list.size() - 1); //start recursive method
    }

    /**
     * Driver quicksort method, with three different ways to determine the pivot
     *
     * @param list- list to be sorted
     */
    public static <T extends Comparable<T>> void quicksort(ArrayList<T> list) {
        quicksort(list, 0, list.size() - 1);

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

    private static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arrayList, ArrayList<T> temp, int left, int right) {
        if (right - left <= INSERTION_THRESHOLD) { //if the subarray is <= INSERTION_SORT_THRESHOLD, use insertion sort to sort
            insertionSort(arrayList, left, right);

        } else {
            int mid = (left + right) / 2; //find mid to split the array
            mergesort(arrayList, temp, left, mid); //recursive call to get to subarray.length <= INSERTION_SORT_THRESHOLD
            mergesort(arrayList, temp, mid + 1, right); //recursive call to get to subarray.length <= INSERTION_SORT_THRESHOLD
            merge(arrayList, temp, left, mid, right); //merges the subarrays together
        }
    }



    private static <T extends Comparable<? super T>> void merge(ArrayList<T> list, ArrayList<T> temp, int left, int mid, int right) {
        int size = mid - left + 1; //length of subarrays

        for (int i = 0; i < size; i++) {
            temp.set(i, list.get(left + i));
        }

        int i = 0; //while loop counter, also subarray cursor
        int j = mid + 1; //while loop counter, also subarray cursor
        int k = left; //array cursor

        while (i < size && j <= right) { //while inside indexes of the subarrays
            if (temp.get(i).compareTo(list.get(j)) <= 0) { //if temp.get(i) <= arrayList.get(j)
                list.set(k, temp.get(i)); //set temp.get(i) to arrayList.get(k)
                i++;
            } else {
                list.set(k, list.get(j)); //set arrayList.get(j) to arrayList.get(k)
                j++;
            }
            k++;
        }

        while (i < size) { //while loop to finish adding values from subarray
            list.set(k++, temp.get(i++));
        }
    }

    private static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> arrayList, int left, int right) {
        for (int i = left+1; i <= right; i++) { //while inbounds of the subarray (setting i to index 1 of the subarray to start the loop)
            T currentIndex = arrayList.get(i); //current index checking in subarray
            int j = i-1; //index below i
            while (j >= left && arrayList.get(j).compareTo(currentIndex) > 0) { //while j is greater than index -1 of the subarray AND arraylist.get(j) > currentIndex
                arrayList.set(j+1, arrayList.get(j)); //swap value to new index
                j--;
            }
            arrayList.set(j+1, currentIndex); //swap value to new index
        }
    }

    private static <T extends Comparable<T>> void quicksort(ArrayList<T> list, int start, int end) {
        if ((start - end) <= INSERTION_THRESHOLD) {
            insertionSort(list, start, end);
            return;
        }

        int pivotIndex = partition(list, start, end);

        // Recursively sort the sublists on either side of the partition
        quicksort(list, start, pivotIndex - 1);
        quicksort(list, pivotIndex + 1, end);

    }

    private static <T extends Comparable<? super T>> int partition(ArrayList<T> list, int start, int end) {

        int pivotIndex = findPivot1(start, end); // using middle for now
        T pivot = list.get(pivotIndex);
        if (start >= end)
            return pivotIndex;
        int indexFromLeft = start;
        int indexFromRight = end;
        //get pivot out of way
        swap(list, pivotIndex, indexFromRight);
        //main loop that iterates through
        while (indexFromRight > indexFromLeft) {
            // find first item on the left that is greater than the pivot
            while (list.get(indexFromLeft).compareTo(pivot) < 0) indexFromLeft++;
            // find first item on the right that is less than the pivot
            while (list.get(indexFromRight).compareTo(pivot) > 0) indexFromRight--;

            swap(list, indexFromLeft, indexFromRight);
        }
        //reassign pivot index
        pivotIndex = indexFromLeft;
        return pivotIndex;

    }

    private static <T> void swap(ArrayList<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    /**
     * Find Pivot 1 returns the index of the middle element for the array that is passed.
     * If the List is of odd length, the index left of the middle is returned.
     *
     * @return int-index for pivot
     */
    private static int findPivot1(int start, int end) {

        return (start - end - 1) / 2;
    }

//    /**
//     * This method returns a pivot index based on an approximate median from First Middle and Last element of the list passed.
//     *
//     * @param list- list given
//     * @return int - pivot index
//     */
//    private static <T extends Comparable<? super T>> int findPivot2(ArrayList<T> list) {
//        // get three elements and add to array list
//        ArrayList<T> arr = new ArrayList<T>();
//        T first = list.getFirst();
//        T middle = list.get(list.size() / 2);
//        T last = list.getLast();
//        arr.add(first);
//        arr.add(middle);
//        arr.add(last);
//        insertionSort(arr);
//        return list.indexOf(arr.get(1));
//
//    }


//    /**
//     * @param list - the list to find the pivot of
//     * @return - the index of the element to pivot around
//     */
//    public static <T> int findPivot3(ArrayList<T> list) {
//        if (list == null || list.isEmpty()) {
//            throw new IllegalArgumentException("Array should not be null or empty");
//        }
//
//        Random rand = new Random();
//        return rand.nextInt(list.size());
//
//
//    }


}
