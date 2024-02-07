package assign04;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class LargestNumberSolver {

    public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            T key = arr[i];
            int j = i - 1;

            // Compare elements and move them to the right until the correct position for the key is found.
            while (j >= 0 && cmp.compare(arr[j], key) < 0) {
                arr[j + 1] = arr[j];
                j --;
            }
            // Place the key in its correct position in the sorted order.
            arr[j + 1] = key;
        }
    }

    public static BigInteger findLargestNumber(Integer[] arr) {

        return new BigInteger(findHelper(arr));
    }

    public static int findLargestInt(Integer[] arr) throws OutOfRangeException {

        return Integer.parseInt(findHelper(arr));
    }

    public static long findLargestLong(Integer[] arr) throws OutOfRangeException {

        return Long.parseLong(findHelper(arr));
    }

    public static BigInteger sum(List<Integer[]> list) {

        return null;
    }

    public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException {

        return new Integer[0];
    }

    public static List<Integer[]> readFile(String filename) {
        List<Integer[]> intArraysList = new ArrayList<>();

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] numbers = line.split("\\s+"); // Split the line into individual numbers

                // Convert the string numbers to integers and create an Integer array
                Integer[] intArray = new Integer[numbers.length];
                // numbers.length is the amount of individual "strings" that have been split across whitespace
                for (int i = 0; i < numbers.length; i++) {
                    intArray[i] = Integer.parseInt(numbers[i]);
                }

                intArraysList.add(intArray);
            }

            // Closes the scanner when done
            scanner.close();

        } catch (FileNotFoundException e) {
            // Handle the case where the file does not exist
            System.err.println("File not found: " + filename);
        }

        return intArraysList;
    }

    private static String findHelper(Integer[] arr) {
    insertionSort(arr, new CustomComparator());

    StringBuilder result = new StringBuilder();
    for (Integer num : arr) {
        result.append(num);
    }

    return result.toString();

    }

    public static class CustomComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer x, Integer y) {
            String XY = x + String.valueOf(y);
            String YX = y + String.valueOf(x);
            return XY.compareTo(YX);
        }
    }

}
?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
    /**
package assign04;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;


/**
 * Public class that finds the largest number combination possible from a given array.
 *
 * @author Soren Fanning and Kieran Griffin
 * @version Feb 8 2024
 */
public class LargestNumberSolver {

    /**
     * Generic insertion sort method for arrays of <T> Objects.
     * Sorts from largest to smallest
     * That utilizes a comparator to sort array.
     *
     * @param arr - Array to be sorted
     * @param cmp - Comparator
     * @param <T> - Generic Type
     */
    public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {
        int size = arr.length;
        for (int i = 1; i < size; i++) {
            T key = arr[i];
            // j is the element index before the key
            int j = i - 1;
            // while arr[j] is greater than arr[i] move j to the right
            while (j >= 0 && cmp.compare(arr[j], key) > 0) {
                arr[j + 1] = arr[j];
                j--;//increment
            }
            //when arr[j] is in the correct spot
            arr[j + 1] = key;
        }
    }


    /**
     * Method determines the largest number possible from the contents of a given integer array.
     *
     * @param arr - Array of numbers
     * @return BigInteger - the largest number possible
     */
    public static BigInteger findLargestNumber(Integer[] arr) {

        return new BigInteger(sortKlargest(arr, 0));
    }

    /**
     * Method determines the largest number possible from the contents of a given integer array.
     *
     * @param arr - The array given
     * @return int - the largest number possible
     * @throws OutOfRangeException - if not in range (-2147483648 to 2147483647)
     */
    public static int findLargestInt(Integer[] arr) throws OutOfRangeException {
        String largest = sortKlargest(arr, 0);
        long largestLong = Long.parseLong(largest);
        if (largestLong > Integer.MAX_VALUE)
            throw new OutOfRangeException("int");


        return Integer.parseInt(largest);
    }

    /**
     * Method determines the largest number possible from the contents of a given integer array.
     *
     * @param arr- Given Array
     * @return long - largest number possible
     */
    public static long findLargestLong(Integer[] arr) throws OutOfRangeException {

        return Long.parseLong(sortKlargest(arr, 0));
    }

    /**
     * This method sums the largest numbers that can be formed by each array in the given list.
     *
     * @param list - list of Integer[] given
     * @return BigInteger - 0 if list is empty otherwise the sum of the given lists
     */
    public static BigInteger sum(List<Integer[]> list) {
//initialize BigInteger for sum and a copy of list
        BigInteger sum = BigInteger.valueOf(0);
        List<Integer[]> listCopy = List.copyOf(list);

        // loop through listCopy and add the largest BigInteger to sum
        for (Integer[] arr : listCopy) {
            BigInteger largest = findLargestNumber(arr);
            sum = sum.add(largest);
        }

        return sum;
    }

    /**
     * This method determines the kth largest number that can be formed by each array in the given list.
     * E.g., if k=0 returns the largest overall, if k=list.size()-1 returns the smallest overall.
     * This method returns the original array that represents the kth largest number, not the kth largest number itself.
     * An IllegalArgumentExceptionLinks to an external site. is thrown if k is not a valid position in the list.
     * This method must not alter the given list and must call your insertionSort method with a Comparator or lambda expression that you design
     *
     * @param list- List of given Integer[]
     * @param k     - the kth largest: ie if k=0 largest is same as findLargestInt()
     * @return Integer[] - the kth largest number that can be formed by each array in the given list.
     * @throws IllegalArgumentException - if k is not a valid index in the array.
     */
    public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException {
        //find min loop to find the array with the smallest length while sorting.

        int shortest = Integer.MAX_VALUE;
        for (Integer[] arr : list) {
            sortKlargest(arr, 0);
            if (arr.length < shortest)
                shortest = arr.length;
        }
//if k out of index bounds for the arrays in list, throw exception
        if (k < 0 || k > shortest - 1)
            throw new IllegalArgumentException("invalid k index for size " + shortest + " array");
//sort all Integer[] in list in decending order

        return new Integer[0];
    }

    /**
     * Creates a List of Integer[] from a given file path
     *
     * @param filename- path of the file
     * @return List<Integer [ ]>  - returns empty list if file not found
     */
    public static List<Integer[]> readFile(String filename) {
        List<Integer[]> intArraysList = new ArrayList<>();

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] numbers = line.split("\\s+"); // Split the line into individual numbers

                // Convert the string numbers to integers and create an Integer array
                Integer[] intArray = new Integer[numbers.length];
                // numbers.length is the amount of individual "strings" that have been split across whitespace
                for (int i = 0; i < numbers.length; i++) {
                    intArray[i] = Integer.parseInt(numbers[i]);
                }

                intArraysList.add(intArray);
            }

            // Closes the scanner when done
            scanner.close();

        } catch (FileNotFoundException e) {
            // Handle the case where the file does not exist
            return intArraysList;
        }

        return intArraysList;
    }


    // Helpers

    /**
     * private helper method that sorts array in decending order
     *
     * @param arr - arr to be sorted
     * @param k   - the k largest int in the arr to be used. USE 0 if you want whole array
     * @return String - largest number combintation
     */
    private static String sortKlargest(Integer[] arr, int k) {
        // Sort the array in descending order using insertion sort with a custom comparator.
        insertionSort(arr, new CustomComparator());

        // Concatenate the sorted integers to create a String representing the largest number.
        StringBuilder result = new StringBuilder();
        for (int i = arr.length - 1; i > k; i--) {
            result.append(arr[i]);
        }

        return result.toString();

    }

    // Custom comparator
    private static class CustomComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer x, Integer y) {
            String XY = x + String.valueOf(y);
            String YX = y + String.valueOf(x);
            return XY.compareTo(YX);
        }
    }

    // main for tests
    public static void main(String[] args) {
        Integer[] arr = {4, 8, 1};
        insertionSort(arr, new CustomComparator());
        System.out.println(Arrays.toString(arr));  // [8, 4, 1]

        Integer[] array1 = {8, 4, 7, 11}; // should be 87411
        Integer[] array2 = {2, 4, 7, 11}; // 74211
        Integer[] array3 = {6, 8, 10, 45, 1}; // 8645110
        Integer[] array4 = {123, 4, 3, 2394, 657, 86, 54, 3, 42};

        BigInteger bigInt1 = findLargestNumber(array1);
        BigInteger bigInt2 = findLargestNumber(array2);
        int largestInt1 = findLargestInt(array3);
        long largestLong1 = findLargestLong(array4);

        System.out.println("Largest BigInt: " + bigInt1);
        System.out.println("Largest BigInt: " + bigInt2);
        System.out.println("Largest int: " + largestInt1);
        System.out.println("Largest long: " + largestLong1);


    }


}
**\
