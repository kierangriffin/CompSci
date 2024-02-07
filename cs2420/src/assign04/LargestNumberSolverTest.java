package assign04;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static assign04.LargestNumberSolver.*;
import static org.junit.jupiter.api.Assertions.*;


public class LargestNumberSolverTest {
    @Test
    void testInsertionSort() {
        Integer[] arr = {4, 2, 7, 11, 9, 3};
        Integer[] expected = {11, 9, 7, 4, 3, 2};
        insertionSort(arr, Comparator.naturalOrder());
        assertArrayEquals(expected, arr);

        Integer[] arr2 = {99, 50, 3, 2, 6, 7, 9};
        Integer[] expected2 = {99, 50, 9, 7, 6, 3, 2};
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
        // Provide the path to a test file with sample data
        String filename = "/Users/kieran/Documents/GitHub/CompSci/cs2420/src/assign04/integers.txt";

        List<Integer[]> result = readFile(filename);

        // Assert that the result is not null
        assertNotNull(result);

        // Add additional assertions based on the content of your test file
        // For example, if your test file has two lines with two integers each:
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

        // Call the readFile method with the path to the created test file
        List<Integer[]> result = readFile(testFilePath.toString());

        // Assert that the result is not null
        assertNotNull(result);

        // Add assertions based on the content of the test file
        assertEquals(3, result.size());
        assertArrayEquals(new Integer[]{1, 2, 3}, result.get(0));
        assertArrayEquals(new Integer[]{4, 5, 6}, result.get(1));
        assertArrayEquals(new Integer[]{7, 8, 9}, result.get(2));
    }

    @Test
    void testReadNonExistentFile() {
        // Call the readFile method with a non-existent file
        List<Integer[]> result = readFile("nonexistentfile.txt");

        // Verify that the result is an empty list
        assertTrue(result.isEmpty());
    }


}
