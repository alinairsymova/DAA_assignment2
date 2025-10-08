package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SelectionSortTest {

    @Test
    void testEmptyArray() {
        int[] arr = {};
        SelectionSort.Metrics m = new SelectionSort.Metrics();
        SelectionSort.selectionSort(arr, m);
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    void testSingleElement() {
        int[] arr = {42};
        SelectionSort.Metrics m = new SelectionSort.Metrics();
        SelectionSort.selectionSort(arr, m);
        assertArrayEquals(new int[]{42}, arr);
    }

    @Test
    void testAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};
        SelectionSort.Metrics m = new SelectionSort.Metrics();
        SelectionSort.selectionSort(arr, m);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    void testReverseOrder() {
        int[] arr = {5, 4, 3, 2, 1};
        SelectionSort.Metrics m = new SelectionSort.Metrics();
        SelectionSort.selectionSort(arr, m);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    void testWithDuplicates() {
        int[] arr = {3, 1, 2, 1, 3};
        SelectionSort.Metrics m = new SelectionSort.Metrics();
        SelectionSort.selectionSort(arr, m);
        assertArrayEquals(new int[]{1, 1, 2, 3, 3}, arr);
    }

    @Test
    void testRandomSmallArray() {
        int[] arr = {9, 2, 7, 4};
        SelectionSort.Metrics m = new SelectionSort.Metrics();
        SelectionSort.selectionSort(arr, m);
        assertArrayEquals(new int[]{2, 4, 7, 9}, arr);
    }
}
