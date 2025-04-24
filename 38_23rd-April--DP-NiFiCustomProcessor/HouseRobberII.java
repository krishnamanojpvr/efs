// You are a stealthy archaeologist exploring a circular ring of ancient tombs 
// located deep within a jungle. Each tomb holds a certain number of precious 
// artifacts. 
// However, these tombs are protected by an ancient magical curse: 
// if you disturb two adjacent tombs during the same night, the entire ring 
// activates a trap that seals you in forever.

// The tombs are arranged in a perfect circle, meaning the first tomb is adjacent 
// to the last. You must plan your artifact retrieval carefully to maximize the 
// number of artifacts collected in a single night without triggering the curse.

// Given an integer array  artifacts  representing the number of artifacts in each 
// tomb, return the   maximum   number of artifacts you can collect without 
// disturbing any two adjacent tombs on the same night.

// Example 1:  
// Input:
// 2 4 3
// Output:  
// 4   

// Explanation: You cannot loot tomb 1 (artifacts = 2) and tomb 3 (artifacts = 3), 
// as they are adjacent in a circular setup.


// Example 2:  
// Input:
// 1 2 3 1
// Output:  
// 4

// Explanation: You can loot tomb 1 (1 artifact) and tomb 3 (3 artifacts) without 
// breaking the ancient rule.  
// Total = 1 + 3 = 4 artifacts.


// Example 3:  
// Input:
// 1 2 3
// Output:  
// 3 

// Constraints:  
// -  1 <= artifacts.length <= 100 
// -  0 <= artifacts[i] <= 1000 

import java.util.*;
public class HouseRobberII{
    public static int memo(int start,int index,int arr[], int dp[]){
        if(index<start) return 0;
        if(index==start){
            return arr[start];
        }
        if(dp[index]!=-1){
            return dp[index];
        }
        return dp[index] = Math.max(arr[index]+memo(start,index-2,arr,dp),memo(start,index-1,arr,dp));
    }
    public static int solve(int arr[]){
        int n= arr.length;
        if(n==1) return arr[0];
        if(n==2) return Math.max(arr[0],arr[1]);
        int dp1[] = new int[n];
        int dp2[] = new int[n];
        Arrays.fill(dp1,-1);
        Arrays.fill(dp2,-1);
        
        int m1 = memo(0,n-2,arr,dp1);
        int m2 = memo(1,n-1,arr,dp2);
        return Math.max(m1,m2);
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int arr[] = new int[s.length];
        for(int i=0;i<s.length;i++){
            arr[i] = Integer.parseInt(s[i]);
        }
        System.out.println(solve(arr));
        sc.close();
    }
}