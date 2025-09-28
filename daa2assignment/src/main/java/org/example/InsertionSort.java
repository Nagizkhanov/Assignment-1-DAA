package org.example;

public class InsertionSort {
    private int comparisons = 0;
    private int swaps = 0;

    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0) {
                comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    swaps++;
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
    }

    public int getComparisons() { return comparisons; }
    public int getSwaps() { return swaps; }
}
