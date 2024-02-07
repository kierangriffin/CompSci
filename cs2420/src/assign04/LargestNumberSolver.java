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
