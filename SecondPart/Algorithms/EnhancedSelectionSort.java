package Algorithms;

public class EnhancedSelectionSort {
    public static void enhancedSelectionSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n / 2; i++) {
            int minIndex = i;
            int maxIndex = i;

            for (int j = i + 1; j < n - i; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
                if (arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                }
            }

            // Swap the minimum element with the leftmost element
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;

            // Adjust maxIndex if it was swapped
            if (maxIndex == i) {
                maxIndex = minIndex;
            }

            // Swap the maximum element with the rightmost element
            temp = arr[maxIndex];
            arr[maxIndex] = arr[n - i - 1];
            arr[n - i - 1] = temp;
        }
    }
}
