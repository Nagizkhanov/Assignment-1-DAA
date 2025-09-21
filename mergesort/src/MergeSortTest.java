import java.util.Random;

public class MergeSortTest{
    public static void main(String[] args) {
        testSortedArray();
        testReverseArray();
        testRandomArray();
        testEmptyArray();
        testSingleElement();
    }

    private static void testSortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        MergeSort.mergeSort(arr);
        printResult(arr);
    }

    private static void testReverseArray() {
        int[] arr = {5, 4, 3, 2, 1};
        MergeSort.mergeSort(arr);
        printResult(arr);
    }

    private static void testRandomArray() {
        Random rand = new Random();
        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++) arr[i] = rand.nextInt();
        MergeSort.mergeSort(arr);
        printResult(arr);
    }

    private static void testEmptyArray() {
        int[] arr = {};
        MergeSort.mergeSort(arr);
        printResult(arr);
    }

    private static void testSingleElement() {
        int[] arr = {42};
        MergeSort.mergeSort(arr);
        printResult(arr);
    }

    private static void printResult(int[] arr) {
        if (isSorted(arr)) System.out.println("Sorted correctly");
        else System.out.println("Sort failed");
    }

    private static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++)
            if (arr[i] < arr[i - 1]) return false;
        return true;
    }
}
