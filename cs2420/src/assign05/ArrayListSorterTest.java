package assign05;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static assign05.ArrayListSorter.*;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayListSorterTest {

    @Test
    public void testMergeSortInteger() {
        ArrayList<Integer> integerList = new ArrayList<>(List.of(4, 2, 8, 5, 1, 66, 6, 3, 7, 34, 45));
        mergesort(integerList);

        ArrayList<Integer> expectedIntegerList = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 34, 45, 66));
        assertEquals(expectedIntegerList, integerList);
    }

    @Test
    public void testMergeSortString() {
        ArrayList<String> stringList = new ArrayList<>(List.of("apple", "orange", "banana", "grape", "kiwi"));
        mergesort(stringList);

        ArrayList<String> expectedStringList = new ArrayList<>(List.of("apple", "banana", "grape", "kiwi", "orange"));
        assertEquals(expectedStringList, stringList);
    }

    // quicksort tests


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





    @Test
    public void testPartion() {
        ArrayList<Integer> integerList = new ArrayList<>(List.of(4, 2, 8, 5, 1, 10, 11, 12));
        System.out.println(integerList.get(integerList.size()/2 -1));
     int f =   assign05.ArrayListSorter.partition(integerList,0,integerList.size()-1);
        print(integerList);
        System.out.println();
        System.out.println();
        System.out.println(f);



    }
    @Test
    public void testPartion02() {
       System.out.println();
        System.out.println();
        ArrayList<Integer> integerList = new ArrayList<>(List.of(4, 2, 8, 5, 1, 10, 11, 12,78,54,78,25,900,-2193,-440));
        System.out.println(integerList.get(integerList.size()/2 -1));
      int g =  assign05.ArrayListSorter.partition(integerList,0,integerList.size()-1);
        print(integerList);
        System.out.println();
        System.out.println();
        System.out.println(g);




    }
public void print(ArrayList<Integer> arr){
        for(int e : arr)
            System.out.print(" "+e+",");



}

}

