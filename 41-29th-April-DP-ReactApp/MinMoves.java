// You are participating in a futuristic space exploration mission where you must 
// navigate through a series of planets aligned in a straight line.
// The planets are numbered from 0 to n-1, and you start your journey on planet 0.

// You are given a 0-indexed array planets, where each element planets[i] represents 
// the maximum number of planets you can move forward from planet i. In simpler terms, 
// standing on planet i, you can move to any planet from i+1 to i+planets[i], 
// as long as you don't exceed the last planet.

// Your goal is to reach the final planet (planet n-1) in the fewest number of 
// moves possible.

// It is guaranteed that a path to the final planet always exists.

// Return the minimum number of moves needed to reach planet n-1.

// Example 1
// ----------
// Input:
// 2 3 1 0 4
// Output:

// 2

// Explanation:
// - Move from planet 0 to planet 1.
// - Move from planet 1 to planet 4 (last planet).


import java.util.*;
public class MinMoves{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String s[] = sc.nextLine().split(" ");
        int n = s.length;
        int arr[] = new int[n];
        for(int i=0;i<n;i++) arr[i] = Integer.parseInt(s[i]);
        int dp[] = new int[n];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i=0;i<n;i++){
            for(int j=i;j<=i+arr[i] && j<n;j++){
                dp[j] = Math.min(dp[j],dp[i]+1);
            }
        }
        System.out.println(dp[n-1]);
        sc.close();
    }
}