import java.util.Scanner;

public class algo17{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the value V
        int V = scanner.nextInt();
        
        // Denominations available in Bangladesh
        int[] denominations = {1000, 500, 200, 100, 50, 20, 10, 5, 2, 1};

        // Variable to store the minimum number of notes
        int count = 0;
        
        // Loop through the denominations
        for (int i = 0; i < denominations.length; i++) {
            // For each denomination, calculate how many notes we can use
            if (V >= denominations[i]) {
                count += V / denominations[i]; // Add the number of notes of this denomination
                V %= denominations[i]; // Reduce the value of V by the amount we've just processed
            }
        }
        
        // Print the result
        System.out.println(count);
    }
}
