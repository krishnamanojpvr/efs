// Alex and his twin brother Jordan often create secret messages. One day, Jordan 
// gives Alex two encrypted messages and challenges him to find the longest common 
// palindromic pattern hidden within both messages.

// Alex wants your help to decode the longest common palindromic subsequence that 
// exists in both strings.

// Your task is to determine the length of the longest subsequence that:
// - Appears in both messages
// - Is a palindrome

// Input Format:
// -------------
// input1: A string representing the first encrypted message.
// input2: A string representing the second encrypted message.

// Output Fromat:
// --------------
// Return an integer representing the length of the longest common palindromic 
// subsequence shared by both messages.


// Sample Input: 
// -------------
// adfa
// aagh

// Sample Output:
// --------------
// 2


// Sample Input-2:
// ---------------
// abcda
// fxaaba

// Sample Output:
// --------------
// 3

// Explanation:
// ------------
// The longest palindromic subsequence common to both is "aba" with length 3.


import java.util.Scanner;

public class LCSP {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        String one = cin.next();
        String two = cin.next();
        System.out.println(find(one, two));
        cin.close();
    }

    static int find(String one, String two) {
        String lcs = getLCS(one, two);
        String palin = getLCS(lcs, new StringBuilder(lcs).reverse().toString());
        return palin.length();

    }

    static String getLCS(String s1, String s2) {
        // System.out.println(s1+" "+s2);
        int n = s1.length();
        int m = s2.length();
        int dp[][] = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        int i = n, j = m;
        StringBuilder lcs = new StringBuilder();
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcs.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        // System.out.println(lcs.reverse());
        return lcs.reverse().toString();
    }
}

// public static int find(String s){
// int len = s.length();
// int dp[][] = new int[len][len];
// for(int i=0;i<len;i++){
// dp[i][i] = 1;
// }
// for(int k=2;k<=len;k++){
// for(int i=0;i<len;i++){
// int j = i+k-1;
// if(s.charAt(i)==s.charAt(j)){
// dp[i][j] = k==2? 2 : dp[i+1][j-1] + 2;
// }else{
// dp[i][j] = Math.max(dp[i+1][j],dp[i][j+1]);
// }
// }
// }
// return dp[0][len];
// }
