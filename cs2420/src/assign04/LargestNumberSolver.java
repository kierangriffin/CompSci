package assign04;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;

public class LargestNumberSolver {

    public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {
        int n = arr.length;

        for (int i = 1; i < n; ++i) {
            T key = arr[i];
            int j = i - 1;

            while (j >= 0 && cmp.compare(arr[j], key) < 0) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }


    public static BigInteger findLargestNumber(Integer[] arr) {
        insertionSort(arr, new CustomComparator());

        StringBuilder result = new StringBuilder();
        for (Integer num : arr) {
            result.append(num);
        }

        return new BigInteger(result.toString());
    }

    public static int findLargestInt(Integer[] arr) throws OutOfRangeException {

        return 0;
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
        Integer[] array1 = {8, 4, 7, 11}; // should be 87411
        Integer[] array2 = {2, 4, 7, 11}; // aaaaaaaa
        Integer[] array3 = {99, 7, 11, 4, 9, 3, 5, 26};
        Integer[] array4 = {6, 8, 10, 45, 1};

        BigInteger largestNumber1 = findLargestNumber(array1);
        BigInteger largestNumber2 = findLargestNumber(array2);
        BigInteger largestNumber3 = findLargestNumber(array3);
        BigInteger largestNumber4 = findLargestNumber(array4);

        System.out.println("Largest Number: " + largestNumber1);
        System.out.println("Largest Number: " + largestNumber2);
        System.out.println("Largest Number: " + largestNumber3);
        System.out.println("Largest Number: " + largestNumber4);

    }


}
