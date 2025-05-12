// Pranav has a puzzle board filled with square boxes in the form of a grid.
// Some cells in the grid may be empty. '0' - indicates empty, '1' - indicates a box. 

// Pranav wants to find out the number of empty spaces which are completely 
// surrounded by the square boxes (left, right, top, bottom) in the board.

// You are given the board in the form of a grid M*N, filled wth 0's and 1's.
// Your task is to help Pranav to find the number of empty groups surrounded by
// the boxes in the puzzle board.

// Input Format:
// -------------
// Line-1: Two integers M and N, the number of rows and columns in the board.
// Next M lines: contains N space-separated either 0 or 1.

// Output Format:
// --------------
// Print an integer, the number of empty spaces in the puzzle board.


// Sample Input-1:
// ---------------
// 6 7
// 1 1 1 1 0 0 1
// 1 0 0 0 1 1 0
// 1 0 0 0 1 1 0
// 0 1 1 1 0 1 0
// 1 1 1 0 0 1 1
// 1 1 1 1 1 1 1

// Sample Output-1:
// ----------------
// 2

// Explanation:
// ------------
// The 2 empty groups are as follows:
// 1st group starts at cell(1,1), 2nd group starts at cell(3,4).
// The groups which are starts at cell(0,4), cell(1,6) and cell(3,0)
// are not valid empty groups, because they are not completely surrounded by boxes.


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
// 1

// Explanation:
// ------------
// The only empty group starts at cell(1,1) is surrounded by boxes.



import java.util.*;
public class ZeroesBetweenOnes{
    public static boolean dfs(int[][] arr, int m,int n,int r,int c, boolean vis[][]){
        if(r<=0 || r>=m-1 || c<=0 || c>=n-1){
            if(arr[r][c]==0){
                return false;
            }
            return true;
        }
        if(arr[r][c]==1 || vis[r][c]) return true;
        vis[r][c] = true;
        return dfs(arr,m,n,r+1,c,vis) & dfs(arr,m,n,r,c+1,vis) & dfs(arr,m,n,r-1,c,vis) & dfs(arr,m,n,r,c-1,vis);
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int arr[][] = new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                arr[i][j] = sc.nextInt();
            }
        }
        int count = 0;
        boolean vis[][] = new boolean[m][n];
        for(int i=1;i<m-1;i++){
            for(int j=1;j<n-1;j++){
                count += (vis[i][j] || arr[i][j]!=0)?0:dfs(arr,m,n,i,j,vis)?1:0;
            }
        }
        System.out.println(count);
    }
}
