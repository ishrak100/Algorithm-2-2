import java.util.Scanner;

class Item {
    int value;
    int weight;
    
    // Constructor
    Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}

public class algo18 {
    // Function to solve the Fractional Knapsack Problem
    public static double getMaxValue(Item[] items, int W, int N) {
        // Calculate value/weight ratio for each item
        double[] ratio = new double[N];
        for (int i = 0; i < N; i++) {
            ratio[i] = (double) items[i].value / items[i].weight;
        }

        // Sort items based on value/weight ratio in descending order
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (ratio[i] < ratio[j]) {
                    // Swap items
                    Item temp = items[i];
                    items[i] = items[j];
                    items[j] = temp;
                    
                    // Swap ratio values
                    double tempRatio = ratio[i];
                    ratio[i] = ratio[j];
                    ratio[j] = tempRatio;
                }
            }
        }

        // Start filling the knapsack
        double totalValue = 0.0;
        int remainingWeight = W;

        for (int i = 0; i < N; i++) {
            if (remainingWeight == 0) break;

            // If item can be fully added
            if (items[i].weight <= remainingWeight) {
                remainingWeight -= items[i].weight;
                totalValue += items[i].value;
            }
            // If item can only be partially added
            else {
                totalValue += items[i].value * ((double) remainingWeight / items[i].weight);
                remainingWeight = 0;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        // Create a scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Input: Number of items
        System.out.print("Enter the number of items: ");
        int N = scanner.nextInt();

        // Array to store the items
        Item[] items = new Item[N];

        // Input: Value and weight for each item
        System.out.println("Enter the value and weight for each item:");
        for (int i = 0; i < N; i++) {
            int value = scanner.nextInt();
            int weight = scanner.nextInt();
            items[i] = new Item(value, weight);
        }

        // Input: Knapsack capacity
        System.out.print("Enter the capacity of the knapsack: ");
        int W = scanner.nextInt();

        // Calculate and print the maximum value that can be obtained
        double maxValue = getMaxValue(items, W, N);
        System.out.println("Maximum value in Knapsack = " + maxValue);

        // Close the scanner object
        scanner.close();
    }
}
