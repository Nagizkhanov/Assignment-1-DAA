package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InsertionSortTest {

    @Test
    public void testEmptyArray() {
        InsertionSort sorter = new InsertionSort();
        int[] arr = {};
        sorter.sort(arr);
        assertArrayEquals(new int[]{}, arr);
        assertEquals(0, sorter.getComparisons());
        assertEquals(0, sorter.getSwaps());
    }

    @Test
    public void testSingleElement() {
        InsertionSort sorter = new InsertionSort();
        int[] arr = {42};
        sorter.sort(arr);
        assertArrayEquals(new int[]{42}, arr);
        assertEquals(0, sorter.getComparisons());
        assertEquals(0, sorter.getSwaps());
    }

    @Test
    public void testSortedArray() {
        InsertionSort sorter = new InsertionSort();
        int[] arr = {1, 2, 3, 4, 5};
        sorter.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
        assertTrue(sorter.getComparisons() > 0);
        assertEquals(0, sorter.getSwaps());
    }

    @Test
    public void testReverseSortedArray() {
        InsertionSort sorter = new InsertionSort();
        int[] arr = {5, 4, 3, 2, 1};
        sorter.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
        assertTrue(sorter.getComparisons() > 0);
        assertTrue(sorter.getSwaps() > 0);
    }

    @Test
    public void testArrayWithDuplicates() {
        InsertionSort sorter = new InsertionSort();
        int[] arr = {3, 1, 2, 3, 1};
        sorter.sort(arr);
        assertArrayEquals(new int[]{1, 1, 2, 3, 3}, arr);
        assertTrue(sorter.getComparisons() > 0);
        assertTrue(sorter.getSwaps() > 0);
    }
}
