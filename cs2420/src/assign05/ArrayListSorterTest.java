package assign05;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static assign05.ArrayListSorter.mergesort;

public class ArrayListSorterTest {

    // Test case for integer list
    @Test
    public void testIntegerMergeSort() {
        ArrayList<Integer> integerList = new ArrayList<>(List.of(4, 2, 8, 5, 1, 6, 3, 7));
        mergesort(integerList);

        ArrayList<Integer> expectedIntegerList = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8));
        Assertions.assertEquals(expectedIntegerList, integerList);
    }

    @Test
    public void testStringMergeSort() {
        ArrayList<String> stringList = new ArrayList<>(List.of("apple", "orange", "banana", "grape", "kiwi"));
        mergesort(stringList);

        ArrayList<String> expectedStringList = new ArrayList<>(List.of("apple", "banana", "grape", "kiwi", "orange"));
        Assertions.assertEquals(expectedStringList, stringList);
    }


}
