package Algorithms;

public class MergeSort {
    public static void mergeSort(int[] arr) {
        //System.out.println(arr.length);
        if (arr.length > 1) {
            int mid = arr.length / 2;

            // Split the array into two halves
            int[] leftHalf = new int[mid];
            int[] rightHalf = new int[arr.length - mid];

            System.arraycopy(arr, 0, leftHalf, 0, mid);
            System.arraycopy(arr, mid, rightHalf, 0, arr.length - mid);

            //System.out.println("LeftHalf");
            mergeSort(leftHalf);
            //System.out.println("RightHalf");
            mergeSort(rightHalf);
           // System.out.println("Done with recursion");
            // Merge the sorted halves
            merge(arr, leftHalf, rightHalf);
        }
    }

    private static void merge(int[] arr, int[] leftHalf, int[] rightHalf) {
        int i = 0, j = 0, k = 0;
        
        // Merge the two halves into the original array
        while (i < leftHalf.length && j < rightHalf.length) {
            if (leftHalf[i] < rightHalf[j]) {
                arr[k] = leftHalf[i];
                i++;
            } else {
                arr[k] = rightHalf[j];
                j++;
            }
            k++;
        }
    
        // Copy remaining elements of leftHalf, if any
        while (i < leftHalf.length) {
            arr[k] = leftHalf[i];
            k++;
            i++;
        }
    
        // Copy remaining elements of rightHalf, if any
        while (j < rightHalf.length) {
            arr[k] = rightHalf[j];
            k++;
            j++;
        }
    }
    
}
