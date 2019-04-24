/*
    QA automation course 2019
    Quicksort algorithm implementation
    See more details at
    https://www.toptal.com/developers/sorting-algorithms/quick-sort
 */

package homework3;

import java.util.Arrays;

public class QuickSort {

    private static void swap(int[] arr, int index_a, int index_b) {
        int tmp = arr[index_a];
        arr[index_a] = arr[index_b];
        arr[index_b] = tmp;
    }

    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int i = (start-1);
        for (int j = start; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i+1, end);
        return i+1;
    }

    private static void sort(int[] arr, int start, int end) {
        if (start < end) {
            int partitionIndex = partition(arr, start, end);
            sort(arr, start, partitionIndex-1);
            sort(arr, partitionIndex+1, end);
        }
    }

    private static void quickSort(int[] arr) {
        sort(arr, 0, arr.length-1);
    }

    private static void printOutput(int[] arr) {
        System.out.println("Non sorted:");
        System.out.println(Arrays.toString(arr));
        quickSort(arr);
        System.out.println("Sorted:");
        System.out.println(Arrays.toString(arr));
        System.out.println("--------++++++++++--------");
    }

    public static void main(String[] args) {
        int[] array1 = {2, 6, -10, 98, 45, 0, -1, 9, 45};
        int[] array2 = {10, 9};
        int[] array3 = {0};
        int[] array4 = {7, 10};
        int[] array5 = {7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
        int[] array6 = {2, 6, -10, 98, 45, 0, -1, 9, 45, 2, 6, -10, 98, 45, 0, -1, 9, 45, 2, 6, -10, 98, 45, 0, -1, 9, 45};

        printOutput(array1);
        printOutput(array2);
        printOutput(array3);
        printOutput(array4);
        printOutput(array5);
        printOutput(array6);
    }
}
