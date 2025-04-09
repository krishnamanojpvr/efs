/*
b) Write a JAVA Program to find shortest sub array with sum at least K 
Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K. If 
there is no non-empty subarray with sum at least K, return -1. 
Example 1: 
Input: A = [1], K = 1 
Output: 1 
Example 2: 
Input: A = [1,2], K = 4 
Output: -1 
Example 3: 
Input: A = [2,-1,2], K = 3 
Output: 3 
Note: 
1 <= A.length <= 50000 -10 ^ 5 <= A[i] <= 10 ^ 5 
1 <= K <= 10 ^ 9 
 */

import java.util.*;

public class Exp_1b {
    public static int count(int arr[], int n, int k) {
        int res = Integer.MAX_VALUE;
        int prefixSum[] = new int[n + 1];
        for (int i = 0; i < n; i++)
            prefixSum[i + 1] = prefixSum[i] + arr[i];
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i <= n; i++) {
            while (!dq.isEmpty() && prefixSum[i] - prefixSum[dq.peekFirst()] >= k) {
                res = Math.min(res, i - dq.pollFirst());
            }
            while (!dq.isEmpty() && prefixSum[i] <= prefixSum[dq.peekLast()]) {
                dq.pollLast();
            }
            dq.addLast(i);
        }
        return res;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        System.out.println(count(arr, n, k));
        sc.close();

    }
}