package assign04;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static assign04.LargestNumberSolver.*;
import static org.junit.jupiter.api.Assertions.*;


public class LargestNumberSolverTest {
    @Test
    void testInsertionSort() {
        Integer[] arr = {4, 2, 7, 11, 9, 3};

        Integer[] expected = {2, 3, 4, 7, 9, 11};
        insertionSort(arr, Comparator.naturalOrder());
        assertArrayEquals(expected, arr);

        Integer[] arr2 = {99, 50, 3, 2, 6, 7, 9};
        Integer[] expected2 = {2, 3, 6, 7, 9, 50, 99};
        insertionSort(arr2, Comparator.naturalOrder());
        assertArrayEquals(expected2, arr2);
    }

    @Test
    void testFindLargestNumber() {
        Integer[] arr1 = {8, 4, 7, 11};
        BigInteger expected1 = new BigInteger("87411");
        BigInteger actual1 = findLargestNumber(arr1);
        assertEquals(expected1, actual1);

        Integer[] arr2 = {2, 4, 7, 11};
        BigInteger expected2 = new BigInteger("74211");
        BigInteger actual2 = findLargestNumber(arr2);
        assertEquals(expected2, actual2);

        Integer[] arr3 = {99999, 88888, 77777, 66666};
        BigInteger expected3 = new BigInteger("99999888887777766666");
        BigInteger actual3 = findLargestNumber(arr3);
        assertEquals(expected3, actual3);

        Integer[] arr4 = {999999999, 888888888, 777777777, 666666666};
        BigInteger expected4 = new BigInteger("999999999888888888777777777666666666");
        BigInteger actual4 = findLargestNumber(arr4);
        assertEquals(expected4, actual4);
    }

    @Test
    void testFindLargestLong() {
        Integer[] arr = {123, 4, 3, 2394, 657, 86, 54, 3, 42};
        long expected = 8665754442332394123L;
        long actual = findLargestLong(arr);
        assertEquals(expected, actual);
    }

    @Test
    void testFindLargestInt() {
        Integer[] arr = {6, 8, 10, 45, 1};
        int expected = 8645110;
        int actual = findLargestInt(arr);
        assertEquals(expected, actual);
    }

    @Test
    void testReadFile() {
        String filename = "/Users/kieran/Documents/GitHub/CompSci/cs2420/src/assign04/integers.txt";

        List<Integer[]> result = readFile(filename);

        assertNotNull(result);

        assertEquals(903, result.size());
        assertArrayEquals(new Integer[]{410, 21, 93, 80, 69, 379, 20, 60, 432, 13, 72, 62, 70, 83, 9, 3, 14, 11, 62, 55,
                34, 83, 80, 99, 56, 25, 79, 51, 51, 70, 79, 20, 34, 67, 40, 51, 41, 94, 89, 116, 874, 554, 137, 371, 17,
                77, 97, 58, 83, 97, 26, 17, 54, 96, 33}, result.getFirst());
    }

    @Test
    void testReadTempFile(@TempDir Path tempDir) throws IOException {
        // Create a small test file within the temporary directory
        Path testFilePath = tempDir.resolve("testfile.txt");
        List<String> lines = List.of("1 2 3", "4 5 6", "7 8 9");
        Files.write(testFilePath, lines);

        List<Integer[]> result = readFile(testFilePath.toString());

        assertNotNull(result);

        assertEquals(3, result.size());
        assertArrayEquals(new Integer[]{1, 2, 3}, result.get(0));
        assertArrayEquals(new Integer[]{4, 5, 6}, result.get(1));
        assertArrayEquals(new Integer[]{7, 8, 9}, result.get(2));
    }

    @Test
    void kThrows() {
        ArrayList<Integer[]> arr = new ArrayList<>();
        arr.add(new Integer[]{4, 5, 6, 8, 4, 4});
        assertThrows(IllegalArgumentException.class, () -> findKthLargest(arr, 2));
    }

    @Test
    void kThrows2() {
        ArrayList<Integer[]> arr = new ArrayList<>();
        arr.add(new Integer[]{4, 5, 6, 8, 4, 4});
        assertThrows(IllegalArgumentException.class, () -> findKthLargest(arr, -1));
    }

    @Test
    void testReadNonExistentFile() {
        List<Integer[]> result = readFile("nonexistentfile.txt");

        assertTrue(result.isEmpty());
    }

    @Test
    void testK0() {
        List<Integer[]> l = new ArrayList<>();
        l.add(new Integer[]{1, 24, 23});
        l.add(new Integer[]{1, 1, 1});
        l.add(new Integer[]{9, 0, 3, 6});
        l.add(new Integer[]{4, 6, 1, 4});
        l.add(new Integer[]{0, 0, 0, 9});
        l.add(new Integer[]{0, 0, 0, 0, 0, 0});
        Integer[] ar1 = findKthLargest(l, l.size() - 1);
        assertArrayEquals(ar1, new Integer[]{0, 0, 0, 0, 0, 0});


    }

    @Test
    void testK1() {
        List<Integer[]> l = new ArrayList<>();
        l.add(new Integer[]{1, 24, 23});
        l.add(new Integer[]{1, 1, 1});
        l.add(new Integer[]{9, 0, 3, 6});
        l.add(new Integer[]{4, 6, 1, 4});
        l.add(new Integer[]{0, 0, 0, 9});
        l.add(new Integer[]{0, 0, 0, 0, 0, 0});
        Integer[] ar1 = findKthLargest(l, l.size() - 2);
        assertArrayEquals(ar1, new Integer[]{1, 1, 1});


    }

    @Test
    void testK3() {
        List<Integer[]> l = new ArrayList<>();
        l.add(new Integer[]{9, 0, 3, 6});
        l.add(new Integer[]{4, 6, 1, 4});
        l.add(new Integer[]{0, 0, 0, 9});
        l.add(new Integer[]{0, 0, 0, 0, 0, 0});
        Integer[] ar1 = findKthLargest(l, 1);
        assertArrayEquals(ar1, new Integer[]{0, 0, 0, 9});


    }

    @Test
    void testK5() {
        List<Integer[]> l = new ArrayList<>();
        l.add(new Integer[]{9, 0, 3, 6});
        l.add(new Integer[]{4, 6, 1, 4});
        l.add(new Integer[]{0, 0, 0, 9});
        l.add(new Integer[]{0, 0, 0, 0, 0, 0});
        Integer[] ar1 = findKthLargest(l, 0);

        assertArrayEquals(ar1, new Integer[]{0, 3, 6, 9});


    }

    @Test
    void testK4() {
        List<Integer[]> l = new ArrayList<>();
        l.add(new Integer[]{6});
        l.add(new Integer[]{4});
        l.add(new Integer[]{2});
        l.add(new Integer[]{0});
        Integer[] ar1 = findKthLargest(l, 0);
        assertArrayEquals(ar1,new Integer[]{6});


    }

    @Test
    public void testSumWithEmptyList() {
        List<Integer[]> emptyList = new ArrayList<>();
        BigInteger result = sum(emptyList);
        assertEquals(BigInteger.ZERO, result);
    }

    @Test
    public void testSumWithSingleArray() {
        List<Integer[]> singleList = Collections.singletonList(new Integer[]{1, 2, 3});
        BigInteger result = sum(singleList);
        assertEquals(BigInteger.valueOf(321), result);
    }


    @Test
    public void testSumWithMultipleArrays() {
        List<Integer[]> multipleList = Arrays.asList(
            new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9},
            new Integer[]{10, 11, 12, 13}
        );

        BigInteger result = sum(multipleList);
        assertEquals(BigInteger.valueOf(1000775431), result);
    }

    @Test
    void testFindKthLargestWithEmptyList() {
        List<Integer[]> emptyList = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> findKthLargest(emptyList, 0));
    }

    @Test
    void testFindKthLargestWithKEqualToListSize() {
        List<Integer[]> list = new ArrayList<>();
        list.add(new Integer[]{1, 2, 3});
        list.add(new Integer[]{4, 5, 6});
        list.add(new Integer[]{7, 8, 9});
        assertThrows(IllegalArgumentException.class, () -> findKthLargest(list, 3));
    }

    @Test
    void testFindKthLargestWithSmallListAndKZero() {
        List<Integer[]> list = new ArrayList<>();
        list.add(new Integer[]{1, 2, 3});

        Integer[] result = findKthLargest(list, 0);

        assertArrayEquals(new Integer[]{1, 2, 3}, result, "findKthLargest should return the original array for k=0");
    }

    @Test
    void testSumDoesNotModifyInputList() {
        List<Integer[]> originalList = new ArrayList<>();
        originalList.add(new Integer[]{1, 2, 3});
        originalList.add(new Integer[]{4, 5, 6});

        List<Integer[]> copyList = new ArrayList<>(originalList);

        sum(originalList);

        assertIterableEquals(copyList, originalList, "The original list should not be modified by the sum method");
    } //wtf

}