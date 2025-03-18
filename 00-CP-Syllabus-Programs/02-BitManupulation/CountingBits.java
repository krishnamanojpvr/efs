
/*
You are given an integer N. Your task is to return an array ans of length N + 1 
where each ans[i] represents the number of 1's in the binary representation of i.

Input Format:
--------------
A single integer N, representing the range from 0 to N.

Output Format:
---------------
An array of N+1 integers where each element represents the count of 1s in the binary representation of each number from 0 to N.

Sample Input-1:
---------------
2

Sample Output-1:
----------------
[0,1,1]

Explanation:
------------
0 --> 0
1 --> 1
2 --> 10

Sample Input-2:
---------------
5

Sample Output-2:
--------------- 
[0,1,1,2,1,2]

Explanation:
------------
0 --> 0
1 --> 1
2 --> 10
3 --> 11
4 --> 100
5 --> 101

*/
import java.util.*;

public class CountingBits {
    // * Brian Kernighan's Algorithm to find number of set bits in O(k) where k is
    // number of set bits
    // * Optimized
    private static int hammingWeightBrian(int n) {
        int count = 0;
        while (n > 0) {
            n &= (n - 1); // * removes the rightmost set(1) bit
            count++;
        }
        return count;
    }

    // * T.C:- O(logn) (Brute Force)
    // private static int countBits(int n) {
    //     int count = 0;
    //     while (n > 0) {
    //         count += (n & 1); // * checks the right most bit whether it is 1 or 0 and adds to count
    //         n >>= 1; // * right shifts the number
    //     }
    //     return count;
    // }

    private static int[] getBinaryArray(int n) {
        int[] bin = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            bin[i] = hammingWeightBrian(i);
        }

        return bin;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(Arrays.toString(getBinaryArray(n)));
        sc.close();
    }
}