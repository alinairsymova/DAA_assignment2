package algorithms;

public class SelectionSort {

    public static class Metrics {
        public long comparisons = 0;
        public long swaps = 0;
    }

    public static void selectionSort(int[] arr, Metrics m) {
        int n = arr.length;
        boolean sorted;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            sorted = true;

            for (int j = i + 1; j < n; j++) {
                m.comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                    sorted = false;
                }
            }

            if (minIndex != i) {
                swap(arr, i, minIndex);
                m.swaps++;
            }

            if (sorted) break;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 5, 6};
        Metrics m = new Metrics();
        selectionSort(arr, m);

        for (int num : arr) {
            System.out.print(num + " ");
        }

        System.out.printf("%nComparisons: %d | Swaps: %d%n", m.comparisons, m.swaps);
    }
}
