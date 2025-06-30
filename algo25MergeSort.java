import java.util.Scanner;

public class algo25MergeSort {

    // Function to merge two subarrays
    public static void merge(int arr[], int s, int e) {
        int mid = (s + e) / 2;
        int n1 = mid - s + 1; // Size of left subarray
        int n2 = e - mid;     // Size of right subarray

        // Temporary arrays
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        // Copy data to temporary arrays
        for (int i = 0; i < n1; i++)
            leftArr[i] = arr[s + i];
        for (int j = 0; j < n2; j++)
            rightArr[j] = arr[mid + 1 + j];

        // Merge the temporary arrays back into arr[left..right]
        int i = 0, j = 0, k = s;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftArr, if any
        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        // Copy remaining elements of rightArr, if any
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    // Recursive function for merge sort
    public static void mergeSort(int arr[], int s, int e) {
        if (s >= e) {
            return;
        }
        int mid = (s + e) / 2;

        // Sort first and second halves
        mergeSort(arr, s, mid);
        mergeSort(arr, mid + 1, e);

        // Merge the sorted halves
        merge(arr, s, e);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Read the size of the array
        int size = scanner.nextInt();
        int[] arr = new int[size];
        
        // Input the elements of the array
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        // Call mergeSort to sort the array
        mergeSort(arr, 0, size - 1);

        // Output the sorted array
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }

        scanner.close();
    }
}
