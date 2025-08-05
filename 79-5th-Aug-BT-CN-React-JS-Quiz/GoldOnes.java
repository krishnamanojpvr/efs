// A merchant has two types of idols, gold and silver.
// He has arranged the idols in the form of m*n grid, 
// 	- the gold idols are represented as 1's 
// 	- the silver idols are represented as 0's.

// Your task is to find the longest consecutive arrangement of gold idols, 
// The arrangement can be either horizontal, vertical, diagonal or 
// antidiagonal, but not the combination of these.


// Input Format:
// -------------
// Line-1: Two space separated integers m and n, grid size.
// Next m lines : n space separated integers, arrangement of idols.

// Output Format:
// --------------
// Print an integer, longest arranement of gold idols.


// Sample Input:
// ---------------
// 4 5
// 1 0 1 1 1
// 0 1 0 1 0
// 1 0 1 0 1
// 1 1 0 1 1

// Sample Output:
// ----------------
// 4

import java.util.*;
public class GolOnes{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int dir[][] = {{1,1},{0,1},{1,0},{1,-1}};
        int m = sc.nextInt();
        int n = sc.nextInt();
        int arr[][] = new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                arr[i][j] = sc.nextInt();
            }
        }
        int max = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(arr[i][j]==1){
                    for(int direction=0;direction<4;direction++){
                        max = Math.max(max,solve(arr,i,j,dir[direction],0));
                    }
                }
            }
        }
        System.out.println(max);
        sc.close();
    }
    public static int solve(int arr[][],int i,int j,int dir[],int curr){
        if(i<0 || i>=arr.length || j<0 || j>=arr[0].length || arr[i][j]==0){
            return curr;
        }
        return solve(arr,i+dir[0],j+dir[1],dir,curr+1);
    }
}