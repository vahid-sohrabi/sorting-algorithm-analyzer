package com.example.core.util;

import java.util.Arrays;

public class SortingUtility {

    public static int[] mergeSort(int[] arr) {
        int[] clonedArray = arr.clone();
        mergeSortHelper(clonedArray, 0, clonedArray.length - 1);
        return clonedArray;
    }

    private static void mergeSortHelper(int[] arr, int left, int right) {
        if (left >= right) return; // Base case: single-element array
        int middle = (right + left) / 2;
        // Recursively sort both halves
        mergeSortHelper(arr, left, middle);
        mergeSortHelper(arr, middle + 1, right);
        // Merge the sorted halves
        merge(arr, left, middle, right);
    }

    private static void merge(int[] arr, int left, int middle, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = middle + 1, k = 0;
        while (i <= middle && j <= right) {
            if (arr[i] <= arr[j]) temp[k++] = arr[i++];
            else temp[k++] = arr[j++];
        }
        while (i <= middle) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        // Copy merged elements back to original array
        System.arraycopy(temp, 0, arr, left, temp.length);
    }

    public static int[] radixSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        int[] arrClone = arr.clone(); // Create a clone of the array to avoid modifying the original
        int max = Arrays.stream(arrClone).max().orElseThrow();

        for (int place = 1; max / place > 0; place *= 10) {
            countingSort(arrClone, place);
        }

        return arrClone; // Return the sorted clone
    }

    private static void countingSort(int[] arr, int place) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10]; // Digits 0-9

        for (int num : arr) {
            int digit = (num / place) % 10;
            count[digit]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / place) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }

    public static int[] insertSort(int[] arr) {
        int size = arr.length;
        // No need to proceed if the array has one or fewer elements
        if (size <= 1) {
            return arr;
        }
        // Create a copy of the array to sort
        int[] sortedArr = arr.clone();
        // Shift elements that are greater than temp to one position ahead of their current position
        for (int i = 1; i < size; i++) {
            int j = i;
            int temp = sortedArr[j];
            while (j > 0 && sortedArr[j - 1] > temp) {
                sortedArr[j] = sortedArr[j - 1];
                j--;
            }
            // Insert the temp value at its correct position
            sortedArr[j] = temp;
        }
        return sortedArr;
    }

    public static int[] selectSort(int[] arr) {
        int size = arr.length;
        // No need to proceed if the array has one or fewer elements
        if (size <= 1) {
            return arr;
        }
        // Create a copy of the array to sort
        int[] sortedArr = arr.clone();
        int temp;
        for (int i = 0; i < size - 1; i++) {
            int minIndex = findMinIndex(sortedArr, i + 1);
            temp = sortedArr[i];
            sortedArr[i] = sortedArr[minIndex];
            sortedArr[minIndex] = temp;
        }
        return sortedArr;
    }

    public static int findMinIndex(int[] arr, int start) {
        if (start >= arr.length) {
            throw new RuntimeException("stat index is greater than array length");
        }
        int minIndex = start; // Assume the min is at the start index
        for (int i = start + 1; i < arr.length; i++) {
            if (arr[i] < arr[minIndex]) {
                minIndex = i; // Update minIndex if a larger element is found
            }
        }
        return minIndex;
    }
}
