import java.util.Scanner;

public class algo20 {

    // Function to calculate the maximum value that can be obtained in the knapsack
    public static int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        
        // Create a DP table where dp[i][w] represents the maximum value for first i items and capacity w
        int[][] dp = new int[n + 1][capacity + 1];

        // Fill the DP table
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                // Base case: No items or capacity is 0, so max value is 0
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } 
                // If the weight of the current item is less than or equal to the capacity
                else if (weights[i - 1] <= w) {
                    // Choose the maximum of two options:
                    // 1. Don't take the current item
                    // 2. Take the current item and add its value to the remaining capacity
                    dp[i][w] = Math.max(dp[i - 1][w], values[i - 1] + dp[i - 1][w - weights[i - 1]]);
                } 
                // If the weight of the current item is more than the capacity, don't take it
                else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        
        // The maximum value that can be obtained is in dp[n][capacity]
        return dp[n][capacity];
    }

    public static void main(String[] args) {
        // Create a Scanner object to take input from the user
        Scanner scanner = new Scanner(System.in);

        // Take input from the user
        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();  // Number of items

        int[] weights = new int[n];
        int[] values = new int[n];

        // Input the weights and values of items
        System.out.println("Enter the weights of the items: ");
        for (int i = 0; i < n; i++) {
            weights[i] = scanner.nextInt();
        }

        System.out.println("Enter the values of the items: ");
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
        }

        // Input the capacity of the knapsack
        System.out.print("Enter the capacity of the knapsack: ");
        int capacity = scanner.nextInt();

        // Call the knapsack function to get the maximum value
        int result = knapsack(weights, values, capacity);
        
        // Output the result
        System.out.println("Maximum value that can be obtained in the knapsack: " + result);

        // Close the scanner
        scanner.close();
    }
}

