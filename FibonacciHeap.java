/**
 * File: FibonacciHeap.java
 * Description: a heap of the Fibonacci variety
 */

public class FibonacciHeap {

    private int[] arr;

    public FibonacciHeap() {

    }

    public FibonacciHeap(int cap) {

    }

    public int insert(int val) {
        int old_val = 0;
        return old_val;
    }

    public int find_min() {
        return 0;
    }

    public int delete_min() {
        return 0;
    }

    public void decrease_key() {

    }

    private static void mergeSort(int[] arr, int[] temp, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, temp, left, mid);
            mergeSort(arr, temp, mid + 1, right);
            merge(arr, temp, left, mid, right);
        }
    }
    private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        int i = left, j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        for (k = left; k <= right; k++) {
            arr[k] = temp[k];
        }
    }
    private void resize() {

    }

    private void sink() {

    }

    private void swim() {

    }
}
