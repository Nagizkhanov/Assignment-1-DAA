public class MergeSort {
    private static final int INSERTION_SORT_THRESHOLD = 16;

    public static void mergeSort(int[] arr) {
        int[] aux = new int[arr.length];
        mergeSort(arr, aux, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int[] aux, int low, int high) {
        if (high <= low + INSERTION_SORT_THRESHOLD) {
            insertionSort(arr, low, high);
            return;
        }
        int mid = low + (high - low) / 2;
        mergeSort(arr, aux, low, mid);
        mergeSort(arr, aux, mid + 1, high);
        merge(arr, aux, low, mid, high);
    }

    private static void merge(int[] arr, int[] aux, int low, int mid, int high) {
        for (int k = low; k <= high; k++) aux[k] = arr[k];
        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid) arr[k] = aux[j++];
            else if (j > high) arr[k] = aux[i++];
            else if (aux[j] < aux[i]) arr[k] = aux[j++];
            else arr[k] = aux[i++];
        }
    }

    private static void insertionSort(int[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int current = arr[i];
            int j = i - 1;
            while (j >= low && arr[j] > current) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = current;
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 4, 1, 3};
        mergeSort(arr);
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
