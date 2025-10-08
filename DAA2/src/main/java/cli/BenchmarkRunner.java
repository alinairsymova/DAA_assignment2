package cli;

import algorithms.SelectionSort;
import metrics.PerformanceTracker;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class BenchmarkRunner {

    private static int[] generateArray(int n, String type) {
        int[] arr = new int[n];
        Random rand = new Random();

        switch (type) {
            case "sorted":
                for (int i = 0; i < n; i++) arr[i] = i;
                break;
            case "reversed":
                for (int i = 0; i < n; i++) arr[i] = n - i;
                break;
            case "almost":
                for (int i = 0; i < n; i++) arr[i] = i;
                for (int i = 0; i < n / 20; i++) { // shuffle ~5%
                    int a = rand.nextInt(n);
                    int b = rand.nextInt(n);
                    int tmp = arr[a];
                    arr[a] = arr[b];
                    arr[b] = tmp;
                }
                break;
            default:
                for (int i = 0; i < n; i++) arr[i] = rand.nextInt(n);
        }
        return arr;
    }

    public static void main(String[] args) {
        int n = args.length > 0 ? Integer.parseInt(args[0]) : 1000;
        String type = args.length > 1 ? args[1] : "random";

        System.out.printf("Running SelectionSort with n=%d (%s data)%n", n, type);
        int[] arr = generateArray(n, type);

        PerformanceTracker tracker = new PerformanceTracker();
        tracker.start();

        SelectionSort.Metrics m = new SelectionSort.Metrics();
        SelectionSort.selectionSort(arr, m);

        tracker.stop();
        saveToCSV("results.csv", n, tracker.getElapsedMillis(), m.comparisons, m.swaps);
        tracker.printReport();

        if (!isSorted(arr)) {
            System.out.println("Array not sorted");
        } else {
            System.out.println("Sorting verified");
        }
    }

    private static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) return false;
        }
        return true;
    }

    private static void saveToCSV(String filename, int n, double time, long comparisons, long swaps) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(n + "," + time + "," + comparisons + "," + swaps + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
