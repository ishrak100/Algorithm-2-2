import java.util.HashMap;
import java.util.Scanner;

public class algo19 {
    
    // Memoization: Top-down approach
    public static int fibMemo(int n, HashMap<Integer, Integer> memo) {
        // Base cases
        if (n <= 1) return n;
        
        // Check if result is already in the memo
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        
        // Calculate and store the result in memo
        int result = fibMemo(n - 1, memo) + fibMemo(n - 2, memo);
        memo.put(n, result);
        
        return result;
    }

    // Tabulation: Bottom-up approach
    public static int fibTab(int n) {
        // Base cases
        if (n <= 1) return n;
        
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        // Build the table from bottom up
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static void main(String[] args) {
        // Initialize scanner to take input
        Scanner scanner = new Scanner(System.in);

        // Take input from the user for n
        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();

        // Using Memoization (Top-Down Approach)
        HashMap<Integer, Integer> memo = new HashMap<>();
        System.out.println("fibMemo: " + fibMemo(n, memo));

        // Using Tabulation (Bottom-Up Approach)
        System.out.println("fibTab: " + fibTab(n));

        // Close the scanner after usage
        scanner.close();
    }
}

