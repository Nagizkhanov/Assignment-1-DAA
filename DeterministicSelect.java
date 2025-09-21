import java.util.*;

public class DeterministicSelect {

    // Метод для разбиения на группы по 5 и поиска медиан каждой группы
    public static List<Integer> chunkAndFindMedians(int[] arr) {
        List<Integer> medians = new ArrayList<>();
        for (int i = 0; i < arr.length; i += 5) {
            int end = Math.min(i + 5, arr.length);
            int[] group = Arrays.copyOfRange(arr, i, end);
            Arrays.sort(group);
            medians.add(group[group.length / 2]);
        }
        return medians;
    }

    // Метод partition: делим элементы на <, =, > pivot
    public static List<List<Integer>> partition(int[] arr, int pivot) {
        List<Integer> low = new ArrayList<>();
        List<Integer> high = new ArrayList<>();
        List<Integer> pivots = new ArrayList<>();
        for (int value : arr) {
            if (value < pivot) {
                low.add(value);
            } else if (value > pivot) {
                high.add(value);
            } else {
                pivots.add(value);
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        result.add(low);
        result.add(pivots);
        result.add(high);
        return result;
    }

    // Основной рекурсивный метод поиска k-го по величине (индексация с 0)
    public static int deterministicSelect(int[] arr, int k) {
        if (arr.length <= 5) {
            Arrays.sort(arr);
            return arr[k];
        }
        List<Integer> medians = chunkAndFindMedians(arr);
        int[] medArr = medians.stream().mapToInt(i -> i).toArray();
        int pivot = deterministicSelect(medArr, medArr.length / 2);

        List<List<Integer>> parts = partition(arr, pivot);
        List<Integer> low = parts.get(0);
        List<Integer> pivots = parts.get(1);
        List<Integer> high = parts.get(2);

        if (k < low.size()) {
            return deterministicSelect(low.stream().mapToInt(i -> i).toArray(), k);
        } else if (k < low.size() + pivots.size()) {
            return pivots.get(0);
        } else {
            return deterministicSelect(high.stream().mapToInt(i -> i).toArray(), k - low.size() - pivots.size());
        }
    }

    public static void main(String[] args) {
        int[] array = {9, 12, 5, 7, 20, 15, 3, 1, 8, 14};
        List<Integer> medians = chunkAndFindMedians(array);
        System.out.println("Медианы групп по 5: " + medians);

        int k = 4; // Ищем 5-й по величине элемент
        int result = deterministicSelect(array, k);
        System.out.println((k + 1) + "-й по величине элемент: " + result);
    }
}
