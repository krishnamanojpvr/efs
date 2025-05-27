/*
You are given an array consisting of N integers, and an integer, K. 
Your task is to determine the minimum element in subarrays of size K.

Sample Input1:
--------------
5
10 12 14 11 15

3

Sample Output1:
---------------
10 11 11

Sample Input2:
--------------
5
5 2 1 1 1
4

Sample Output2:
---------------
1 1


*/

import java.util.*;

public class SmallestElementSubArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int k = sc.nextInt();
        ArrayList<Integer> al = new ArrayList<>();
        // int i = 0, j = 0;
        // int min = Integer.MAX_VALUE;
        // int minIndex = j;
        // while (j < n) {
        //     if (min > arr[j]) {
        //         min = arr[j];
        //         minIndex = j;
        //     }
        //     if (j - i + 1 == k) {
        //         al.add(min);
        //         if (minIndex == i) {
        //             minIndex = i + 1;
        //             min = arr[minIndex];
        //         }
        //         i++;
        //     }
        //     j++;

        // }
        // System.out.print(al);
        // al.clear();
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        for (int c = 0; c < n; c++) {
            while (!dq.isEmpty() && dq.peekFirst() < c - k + 1) {
                dq.pollFirst();
            }
            while (!dq.isEmpty() && arr[dq.peekLast()] > arr[c]) {
                dq.pollLast();
            }
            dq.addLast(c);
            if (c + 1 >= k)
                al.add(arr[dq.peekFirst()]);
        }
        System.out.print(al);
        sc.close();
    }
}