// A Builder builds a wall in a strange way.
// The wall has to built with a width of W.
// And there are N building-blocks are available with some width and height.

// The wall is built as follows:
// 	- Pick the first few buiding-blocks and place them in the available 
// 	width of the wall.
// 	- Once no more building block in the order can't be kept in the available 
// 	width of the wall, place a concrete rack on the highest building-block 
// 	among the row of building blocks.
// 	- Construct the rest of the wall by repeating above two steps until
// 	all the blocks used.

// Your task is to find the minimum possible height of the wall built 
// with width W, after using all N building blocks.


// Input Format:
// -------------
// Line-1: Two space separaed integers, N and W.
// Next N lines: Two space separaed integers, width and height of the brick.

// Output Format:
// --------------
// Print an integer result.


// Sample Input-1:
// ---------------
// 7 5
// 1 2
// 2 2
// 2 3
// 2 3
// 1 4
// 3 4
// 4 2

// Sample Output-1:
// ----------------
// 11


// Sample Input-2:
// ---------------
// 5 3
// 1 1
// 2 2
// 1 2
// 2 3
// 3 2

// Sample Output-2:
// ----------------
// 7

import java.util.*;
public class FindMaxHeight{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int w = sc.nextInt();
        int arr[][] = new int[n][2];
        for(int i=0;i<n;i++){
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }
        
        int memo[][] = new int[n][w+1];
        for(int i[] : memo){
            Arrays.fill(i,-1);
        }
        System.out.println(helper(arr,memo,n,w,w,0,0));
        sc.close();
        
    }
    public static int helper(int arr[][],int memo[][], int n,int maxw,int remw,int maxh,int i){
        if(i==arr.length) return maxh;
        if(memo[i][remw]!=-1) return memo[i][remw];
        
        int nextrow = maxh + helper(arr,memo,n,maxw,maxw-arr[i][0],arr[i][1],i+1);
        int currrow = Integer.MAX_VALUE;
        if(remw>=arr[i][0]) currrow = helper(arr,memo,n,maxw,remw-arr[i][0],Math.max(maxh,arr[i][1]),i+1);
        return memo[i][remw] = Math.min(nextrow,currrow);
    }
}