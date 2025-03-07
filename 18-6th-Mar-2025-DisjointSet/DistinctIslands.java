// package 18-6th-Mar-2025-DisjointSet;

// public class DistinctIslands {
    
// }
// Pranav has a puzzle board filled with square boxes in the form of a grid. Some 
// cells in the grid may be empty. '0' - indicates empty, '1' - indicates a box. 

// The puzzle board has some patterns formed with boxes in it, 
// the patterns may be repeated. The patterns are formed with boxes (1's) only, 
// that are connected horizontally and vertically but not diagonally.

// Pranav wants to find out the number of unique patterns in the board.

// You are given the board in the form of a grid M*N, filled wth 0's and 1's.
// Your task is to help Pranav to find the number of unique patterns in 
// the puzzle board.

// Input Format:
// -------------
// Line-1: Two integers M and N, the number of rows and columns in the grid-land.
// Next M lines: contains N space-separated integers [0, 1].

// Output Format:
// --------------
// Print an integer, the number of unique patterns in the puzzle board.


// Sample Input-1:
// ---------------
// 5 5
// 0 1 0 1 1
// 1 1 1 0 1
// 0 1 0 1 0
// 1 0 1 1 1
// 1 1 0 1 0

// Sample Output-1:
// ----------------
// 3

// Explanation-1:
// ------------
// The unique patterns are as follows:
//   1			1 1	    1 
// 1 1 1		  1 ,	1 1
//   1	   ,	
   
   
// Sample Input-2:
// ---------------
// 6 6
// 1 1 0 0 1 1
// 1 0 1 1 0 1
// 0 1 0 1 0 0
// 1 1 0 0 0 1
// 0 0 1 0 1 1
// 1 1 0 1 0 0

// Sample Output-2:
// ----------------
// 5

// Explanation-2:
// ------------
// The unique patterns are as follows:
// 1 1		1 1		    1		1 1	,	1
// 1   ,     1 ,	    1 1 ,		


import java.util.*;
public class DistinctIslands{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int arr[][] = new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                arr[i][j] = sc.nextInt();
            }
        }
        Queue<int[]> q = new LinkedList<>();
        int dir[][] = {{1,0},{-1,0},{0,1},{0,-1}};
        String d = "durl";
        Set<String> set = new HashSet<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(arr[i][j]==1){
                    StringBuilder sb = new StringBuilder();
                    arr[i][j]=0;
                    q.add(new int[]{i,j});
                    while(!q.isEmpty()){
                        int[] top = q.poll();
                        for(int k=0;k<4;k++){
                            int nr = dir[k][0]+top[0];
                            int nc = dir[k][1]+top[1];
                            if(nr>=0 && nr<m && nc>=0 && nc<n && arr[nr][nc]==1){
                                arr[nr][nc] = 0;
                                q.add(new int[]{nr,nc});
                                sb.append(d.charAt(k));
                            }
                            else{
                                sb.append("b");
                            }
                        }
                    }
                    set.add(sb.toString());
                }
            }
        }
        System.out.println(set.size());
        sc.close();
    }
}