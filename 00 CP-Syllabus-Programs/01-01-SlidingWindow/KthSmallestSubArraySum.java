/*

A tenth standard student has been given a task to analyze a set of P subject marks.

Given an integer I, student needs to determine the I-th least sum among all possible contiguous subarrays of the marks list.

Input Format:
--------------
Line-1: Two space-separated integers, P (number of subjects) and I (the required index in the sorted sums list).
Line-2: P space-separated integers, representing the marks in each subject.

Output Format:
--------------
Line-1: Print a single integer, which is the I-th least sum among all possible contiguous subarrays.

Sample Input-1:
--------------
3 4
3 2 4

Sample output-1:
---------------
5

Explanation: 
------------
The subarrays of 3 2 4 are:
1st subarray: 3 the sum is 3
2nd subarray: 2 the sum is 2
3rd subarray: 4 the sum is 4
4th subarray: 3,2 the sum is 5
5th subarray: 2,4 the sum is 6
6th subarray: 3,2,4 the sum is 9

The 4th smallest is 5

Sample Input-2:
---------------
4 7
2 2 4 4

Sample output-2:
----------------
8

Explanation: 
------------
The subarrays of 2 2 4 4 are

1st subarray: 2 the sum is 2
2nd subarray: 2 the sum is 2
3rd subarray: 4 the sum is 4
4th subarray: 4 the sum is 4
5th subarray: 2,2 the sum is 4
6th subarray: 2,4 the sum is 6
7th subarray: 4,4 the sum is 8
8th subarray: 2,2,4 the sum is 8
9th subarray: 2,4,4 the sum is 10
10th subarray: 2,2,4,4 the sum is 12

The 7th smallest is 8
 */

import java.util.*;

public class KthSmallestSubArraySum {

    // * SlidingWindow-PriorityQueue
    public static int kthSmallest(int[] arr, int n, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < k; i++) {
            int sum = 0;
            for (int j = i; j < k; j++) {
                sum += arr[j];
                maxHeap.add(sum);
                if (maxHeap.size() > k) {
                    maxHeap.poll();
                }
            }
        }
        return maxHeap.peek();

    }

    // * Using BinarySearch-SlidingWindow
    public static int kthSmallestBins(int[] arr, int n, int k) {
        int min = Integer.MAX_VALUE, max = 0;
        for (int i : arr) {
            min = Math.min(min, i);
            max += i;
        }

        int low = min, high = max;
        while (low < high) {
            int mid = (low + high) / 2;
            if (countSubArraysLessThanOrEqual(arr, mid) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static int countSubArraysLessThanOrEqual(int[] arr, int mid) {
        int count = 0, left = 0, sum = 0;
        for (int right = 0; right < arr.length; right++) {
            sum += arr[right];
            while (sum > mid && left <= right) {
                sum -= arr[left++];
            }
            count += (right - left + 1);
        }
        return count;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.print(kthSmallest(arr, n, k));
        sc.close();
    }

}