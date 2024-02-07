package assign04;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LargestNumberSolver {

    public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {
        int n = arr.length;

        for (int i = 1; i < n; ++i) {
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

        return 0;
    }

    public static BigInteger sum(List<Integer[]> list) {

        return null;
    }

    public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException {

        return new Integer[0];
    }

    public static List<Integer[]> readFile(String filename) {

        return null;
    }

    // Helpers

    private static String findHelper(Integer[] arr) {
        insertionSort(arr, new CustomComparator());

        StringBuilder result = new StringBuilder();
        for (Integer num : arr) {
            result.append(num);
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

        BigInteger bigInt1 = findLargestNumber(array1);
        BigInteger bigInt2 = findLargestNumber(array2);
        int largestInt1 = findLargestInt(array3);

        System.out.println("Largest BigInt: " + bigInt1);
        System.out.println("Largest BigInt: " + bigInt2);
        System.out.println("Largest int: " + largestInt1);



    }


}
