import java.util.Scanner;

public class algo16 {
    public static void main(String[] args) {
        // Create a scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Input: The number of days
        System.out.print("Enter the number of days: ");
        int n = scanner.nextInt();

        // Input: The prices for each day
        int[] prices = new int[n];
        System.out.println("Enter the prices for each day:");
        for (int i = 0; i < n; i++) {
            prices[i] = scanner.nextInt();
        }

        // Output the maximum profit
        System.out.println("Maximum Profit: " + maxProfit(prices));

        // Close the scanner object
        scanner.close();
    }

    public static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE; // Initialize minimum price
        int maxProfit = 0; // Initialize maximum profit

        // Iterate through the prices array
        for (int price : prices) {
            // Update minPrice to the lowest price encountered
            minPrice = Math.min(minPrice, price);

            // Calculate the profit if we sold at the current price
            int profit = price - minPrice;

            // Update maxProfit if the current profit is higher than the previously recorded
            // maxProfit
            maxProfit = Math.max(maxProfit, profit);
        }

        return maxProfit; // Return the maximum profit achieved
    }
}
