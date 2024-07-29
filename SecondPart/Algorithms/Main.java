package Algorithms;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] sizes = {10000, 100000};
//        int[] sizes = {10};
        String[] types = {"sorted", "random", "reversely sorted"};

        for (int size : sizes) {
            for (String type : types) {
                int[] array = generateArray(size, type);
                runExperiment(array, type, size, "MergeSort");
                array = generateArray(size, type);
                runExperiment(array, type, size, "EnhancedSelectionSort");
                //System.out.println("F");
            }
        }
        System.out.println("Done!");
    }

    public static void runExperiment(int[] array, String type, int size, String algorithm) {
        long startTime = System.nanoTime();
        if (algorithm.equals("MergeSort")) {
            MergeSort.mergeSort(array);
        } else if (algorithm.equals("EnhancedSelectionSort")) {
            EnhancedSelectionSort.enhancedSelectionSort(array);
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000; // Convert to milliseconds

        System.out.println("Algorithm: " + algorithm + ", Type: " + type + ", Size: " + size + ", Time: " + duration + " ms");
    }

    public static int[] generateArray(int size, String type) {
        int[] array = new int[size];
        Random random = new Random();

        switch (type) {
            case "sorted":
                for (int i = 0; i < size; i++) {
                    array[i] = i;
                }
                break;
            case "random":
                for (int i = 0; i < size; i++) {
                    array[i] = random.nextInt(size);
                }
                break;
            case "reversely sorted":
                for (int i = 0; i < size; i++) {
                    array[i] = size - i;
                }
                break;
        }
        return array;
    }
}
