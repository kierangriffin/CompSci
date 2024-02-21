package assign05;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static assign05.ArrayListSorter.*;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayListSorterTest {

    public ArrayList<Integer> smallIntArrayList = new ArrayList<>();
    public ArrayList<Integer> mediumIntArrayList = new ArrayList<>();
    public ArrayList<Integer> largeIntArrayList = new ArrayList<>();
    public ArrayList<String> smallStringArrayList = new ArrayList<>();
    public ArrayList<String> mediumStringArrayList = new ArrayList<>();
    public ArrayList<String> largeStringArrayList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        Random rand = new Random();

        for (int i = 0; i <= 20; i++) {
            smallIntArrayList.add(rand.nextInt(20));
            smallStringArrayList.add(generateString());
        }
        for (int i = 0; i <= 100; i++) {
            mediumIntArrayList.add(rand.nextInt(100));
            mediumStringArrayList.add(generateString());
        }
        for (int i = 0; i <= 1000; i++) {
            largeIntArrayList.add(rand.nextInt(1000));
            largeStringArrayList.add(generateString());
        }
    }

    private String generateString() {
        Random rand = new Random();
        int length = rand.nextInt(10) + 1; // length of random strings
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(characters.length());
            stringBuilder.append(characters.charAt(index));
        }
        return stringBuilder.toString();
    }

    // mergesort tests

    @Test
    public void testMergesortSmallInteger() {
        mergesort(smallIntArrayList);
        for (int i = 0; i < smallIntArrayList.size()-1; i++) {
            assertFalse(smallIntArrayList.get(i) > smallIntArrayList.get(i + 1), "ArrayList is not sorted");
        }

    }

    @Test
    public void testMergesortMediumInteger() {
        mergesort(mediumIntArrayList);
        for (int i = 0; i < mediumIntArrayList.size()-1; i++) {
            assertFalse(mediumIntArrayList.get(i) > mediumIntArrayList.get(i + 1), "ArrayList is not sorted");
        }

    }

    @Test
    public void testMergesortLargeInteger() {
        mergesort(largeIntArrayList);
        for (int i = 0; i < largeIntArrayList.size() - 1; i++) {
            assertFalse(largeIntArrayList.get(i) > largeIntArrayList.get(i + 1), "ArrayList is not sorted");
        }
    }

    @Test
    public void testMergesortSmallString() {
        mergesort(smallStringArrayList);
        for (int i = 0; i < smallStringArrayList.size()-1; i++) {
            assertFalse(smallStringArrayList.get(i).compareTo(smallStringArrayList.get(i+1)) > 0, "ArrayList is not sorted");
        }
    }

    @Test
    public void testMergesortMediumString() {
        mergesort(mediumStringArrayList);
        for (int i = 0; i < mediumStringArrayList.size()-1; i++) {
            assertFalse(mediumStringArrayList.get(i).compareTo(mediumStringArrayList.get(i+1)) > 0, "ArrayList is not sorted");
        }
    }

    @Test
    public void testMergesortLargeString() {
        mergesort(largeStringArrayList);
        for (int i = 0; i < largeStringArrayList.size()-1; i++) {
            assertFalse(largeStringArrayList.get(i).compareTo(largeStringArrayList.get(i+1)) > 0, "ArrayList is not sorted");
        }
    }

    // quicksort tests

    @Test
    public void testQuicksortSmallInteger() {
        quicksort(smallIntArrayList);
        for (int i = 0; i < smallIntArrayList.size()-1; i++) {
            assertFalse(smallIntArrayList.get(i) > smallIntArrayList.get(i + 1), "ArrayList is not sorted");
        }

    }

    @Test
    public void testQuicksortMediumInteger() {
        quicksort(mediumIntArrayList);
        for (int i = 0; i < mediumIntArrayList.size()-1; i++) {
            assertFalse(mediumIntArrayList.get(i) > mediumIntArrayList.get(i + 1), "ArrayList is not sorted");
        }

    }

    @Test
    public void testQuicksortLargeInteger() {
        quicksort(largeIntArrayList);
        for (int i = 0; i < largeIntArrayList.size() - 1; i++) {
            assertFalse(largeIntArrayList.get(i) > largeIntArrayList.get(i + 1), "ArrayList is not sorted");
        }
    }

    @Test
    public void testQuicksortSmallString() {
        quicksort(smallStringArrayList);
        for (int i = 0; i < smallStringArrayList.size()-1; i++) {
            assertFalse(smallStringArrayList.get(i).compareTo(smallStringArrayList.get(i+1)) > 0, "ArrayList is not sorted");
        }
    }

    @Test
    public void testQuicksortMediumString() {
        quicksort(mediumStringArrayList);
        for (int i = 0; i < mediumStringArrayList.size()-1; i++) {
            assertFalse(mediumStringArrayList.get(i).compareTo(mediumStringArrayList.get(i+1)) > 0, "ArrayList is not sorted");
        }
    }

    @Test
    public void testQuicksortLargeString() {
        quicksort(largeStringArrayList);
        for (int i = 0; i < largeStringArrayList.size()-1; i++) {
            assertFalse(largeStringArrayList.get(i).compareTo(largeStringArrayList.get(i+1)) > 0, "ArrayList is not sorted");
        }
    }

    // generateAscending tests
    @Test
    public void testGenerateAscending1() {
        int size = 5;
        ArrayList<Integer> result = generateAscending(size);

        // Check if the result is not null
        Assertions.assertNotNull(result);

        // Check if the size of the generated list is equal to the specified size
        assertEquals(size, result.size());

        // Check if the elements are in ascending order
        for (int i = 0; i < size - 1; i++) {
            assertTrue(result.get(i) < result.get(i + 1));
        }
    }

    @Test
    public void testGenerateAscending2() {
        int size = 0;
        ArrayList<Integer> result = generateAscending(size);

        // Check if the result is not null
        Assertions.assertNotNull(result);

        // Check if the generated list is empty
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGenerateAscending3() {
        int size = -5;
        ArrayList<Integer> result = generateAscending(size);

        // Check if the result is not null
        Assertions.assertNotNull(result);

        // Check if the generated list is empty for negative size
        assertTrue(result.isEmpty());
    }

    // generatePermuted tests
    @Test
    public void testGeneratePermuted1() {
        int size = 5;
        ArrayList<Integer> result = generatePermuted(size);

        Assertions.assertNotNull(result);
        assertEquals(size, result.size());

        // Check if the elements are not in ascending order
        for (int i = 0; i < size - 1; i++) {
            assertNotEquals(result.get(i), result.get(i + 1));
        }
    }

    @Test
    public void testGeneratePermuted2() {
        int size = 0;
        ArrayList<Integer> result = generatePermuted(size);

        Assertions.assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGeneratePermuted3() {
        int size = 1000;
        ArrayList<Integer> result = generatePermuted(size);

        Assertions.assertNotNull(result);
        assertEquals(size, result.size());

        // Check if the elements are not in ascending order
        for (int i = 0; i < size - 1; i++) {
            assertNotSame(result.get(i), result.get(i + 1));
        }
    }

    // tests generateDescending
    @Test
    public void testGenerateDescending1() {
        int size = 5;
        ArrayList<Integer> result = generateDescending(size);

        Assertions.assertNotNull(result);
        assertEquals(size, result.size());

        // Check if the elements are in descending order
        for (int i = 0; i < size - 1; i++) {
            assertTrue(result.get(i) > result.get(i + 1));
        }
    }

    @Test
    public void testGenerateDescending2() {
        int size = 0;
        ArrayList<Integer> result = generateDescending(size);

        Assertions.assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGenerateDescending3() {
        int size = 1000;
        ArrayList<Integer> result = generateDescending(size);

        Assertions.assertNotNull(result);
        assertEquals(size, result.size());

        // Check if the elements are in descending order
        for (int i = 0; i < size - 1; i++) {
            assertTrue(result.get(i) > result.get(i + 1));
        }
    }


}
