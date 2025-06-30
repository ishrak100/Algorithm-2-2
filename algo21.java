import java.util.Scanner;

public class algo21 {

    // Function to calculate the length of the longest common subsequence and print the LCS
    public static String lcs(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        
        // Create a 2D array to store the lengths of longest common subsequences
        int[][] dp = new int[m + 1][n + 1];
        
        // Build the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // If the characters match, add 1 to the result of the previous substring
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // Otherwise, take the maximum of the previous subsequences
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        // Backtrack to find the LCS
        StringBuilder lcsString = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcsString.append(s1.charAt(i - 1)); // Append matching character to LCS
                i--;
                j--;
            } else if (dp[i - 1][j] >= dp[i][j - 1]) {
                i--; // Move up in the table
            } else {
                j--; // Move left in the table
            }
        }

        // Reverse the string since we built it backwards
        return lcsString.reverse().toString();
    }

    public static void main(String[] args) {
        // Create a scanner object to take input from the user
        Scanner scanner = new Scanner(System.in);

        // Take input for the two strings
        System.out.print("Enter the first string: ");
        String s1 = scanner.nextLine();
        
        System.out.print("Enter the second string: ");
        String s2 = scanner.nextLine();

        // Call the lcs function to calculate the LCS string
        String lcsString = lcs(s1, s2);
        
        // Output the result in the requested format
        System.out.println(lcsString.length() + " (The LCS is \"" + lcsString + "\")");
        
        // Close the scanner
        scanner.close();
    }
}
