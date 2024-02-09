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
            int j = i - 1;

            while (j >= 0 && cmp.compare(arr[j], key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
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

        return new BigInteger(findLargestNumberHelper(arr));
    }

    /**
     * Method determines the largest number possible from the contents of a given integer array.
     *
     * @param arr - The array given
     * @return int - the largest number possible
     * @throws OutOfRangeException - if not in range (-2147483648 to 2147483647)
     */
    public static int findLargestInt(Integer[] arr) throws OutOfRangeException {
        String largest = findLargestNumberHelper(arr);
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

        return Long.parseLong(findLargestNumberHelper(arr));
    }

    /**
     * This method sums the largest numbers that can be formed by each array in the given list.
     *
     * @param list - list of Integer[] given
     * @return BigInteger - 0 if list is empty otherwise the sum of the given lists
     */
    public static BigInteger sum(List<Integer[]> list) {//wtf

        BigInteger sum = BigInteger.valueOf(0);
        List<Integer[]> listCopy = new ArrayList<>(list);

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
    @SuppressWarnings("ComparatorCombinators")
    public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException {

        Integer[][] arrayOfInt = new Integer[list.size()][];
        if (k < 0 || k > list.size() - 1)
            throw new IllegalArgumentException("invalid k index for size " + list.size() + " array");

        for (int i = 0; i < list.size(); i++)
            arrayOfInt[i] = list.get(i);

        insertionSort(arrayOfInt, (arr1, arr2) -> findLargestNumberHelper(arr1).compareTo
                (findLargestNumberHelper(arr2)));


        return arrayOfInt[(arrayOfInt.length - 1) - k];
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
                String[] numbers = line.split("\\s+");

                Integer[] intArray = new Integer[numbers.length];

                for (int i = 0; i < numbers.length; i++) {
                    intArray[i] = Integer.parseInt(numbers[i]);
                }

                intArraysList.add(intArray);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            return intArraysList;
        }

        return intArraysList;
    }

    /**
     * private helper method that sorts array in ascending order
     *
     * @param arr - arr to be sorted
     * @return String - largest number combination
     */
    private static String findLargestNumberHelper(Integer[] arr) {
        insertionSort(arr, new CustomComparator());

        StringBuilder result = new StringBuilder();
        for (int i = arr.length - 1; i > -1; i--) {
            result.append(arr[i]);
        }

        return result.toString();

    }

    /**
     * Custom comparator for finding the largest number combinations
     */
    private static class CustomComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer x, Integer y) {
            String XY = x + String.valueOf(y);
            String YX = y + String.valueOf(x);
            return XY.compareTo(YX);
        }

    }


}
