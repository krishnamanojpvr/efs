// Luke likes to construct and play with arrays. His dad gave him an array M of 
// length N and assigned him the following tasks to be done in order:
//  - reate continuous groups of size i from the array M (where i goes from 1 to N).
//  - For each group of size i, find the minimum value.
//  - Among all the minimums from that step, select the maximum.
//  - Add this selected value as the i-th element of a new array P.
//  - Repeat until i = N.

// Note: Use 1-based indexing for array P in logic.

// Input Format:
// -------------
// input1: Integer N – length of array M
// input2: Integer array M of length N

// Output Format:
// --------------
// Return the array P as output.

// Sample Input:
// -------------
// 3
// 1 2 3

// Sample Output:
// --------------
// 3 2 1


// Sample Input: 
// -------------
// 4
// 20 10 30 40

// Sample Output: 
// --------------
// 40 30 10 10

import java.util.*;
public class MaxGroups{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for(int i=0;i<n;i++) arr[i] = sc.nextInt();
        int dp[][] = new int[n][n];
        int res[] = new int[n];
        
        int max = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            dp[i][i] = arr[i];
            max = Math.max(max,arr[i]);
        } 
        
        res[0] = max;
        
        for(int len=2;len<=n;len++){
            max = Integer.MIN_VALUE;
            for(int i=0;i<=n-len;i++){
                int j = i+len-1;
                dp[i][j] = Math.min(dp[i][j-1],dp[i+1][j]);
                max = Math.max(dp[i][j],max);
            }
            res[len-1] = max;
        }
        
        System.out.println(Arrays.toString(res));
        sc.close();
        
    }
}
