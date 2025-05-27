/*a) Write a JAVA Program to find Subarrays with K Different integers 

Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A 
good if the number of different integers in that subarray is exactly K. (For example, [1,2,3,1,2] has 
3 different integers: 1, 2, and 3.) 
Return the number of good subarrays of A. 

Example 1: 
Input: A = [1,2,1,2,3], K = 2 
Output: 7 
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], 
[2,1,2], [1,2,1,2] 
Example 2: 
Input: A = [1,2,1,3,4], K = 3 
Output: 3 
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], 
[1,3,4]. Note: 
1 <= A.length <= 20000 
1 <= A[i] <= A.length 
1 <= K <= A.length */

import java.util.*;

public class Exp_1a {

    public static int subarraysWithKDistinct(int arr[],int k){
        return countAtMost(arr,k) - countAtMost(arr,k-1);
    }
    public static int countAtMost(int arr[], int k){
        int l=0, res=0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int r=0;r<arr.length;r++){
            if(map.getOrDefault(arr[r],0)==0) k--;
            map.put(arr[r],map.getOrDefault(arr[r],0)+1);
            while(k<0){
                map.put(arr[l],map.get(arr[l])-1);
                if(map.get(arr[l])==0)k++;
                l++;
            }
            res+=r-l+1;
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
        System.out.println(subarraysWithKDistinct(arr, k));
        sc.close();
    }

}