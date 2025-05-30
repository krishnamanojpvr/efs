// A forest is represented as a square grid consist of 0's an 1's only.
// Few people stuck in the forest, where 1's are indicate people, 
// and 0's are indicate as trees. A person cannot move from the tree.
// You want to save people, a person can be saved, if he/she connected to other 
// person who is at the boundary of forest.
// If 'a' is connected to 'b' and 'b' is connected to 'c',
// then you can consider that 'a' is also connected to 'c'. 
// They can connected in 4 directions only (up, right, left and down).

// You need to find out the number of persons, whom cannot be saved from 
// the given forest grid.


// Input Format:
// -------------
// Line-1 -> An integer N, size of the grid N*N
// next N lines -> N space separated integers(0's and 1's)

// Output Format:
// --------------
// Print an integer as your result


// Sample Input-1:
// ---------------
// 5
// 0 0 1 1 0
// 1 0 0 1 0
// 0 0 1 0 0
// 0 1 1 0 1
// 1 0 0 1 0

// Sample Output-1:
// ----------------
// 3

// Explanation:
// ------------
// In the given grid, 1's at (2,2), (3,1), (3,2) positions are not connected 
// to the boundary. So, number of people cannot be saved are 3

// Sample Input-2:
// ---------------
// 5
// 0 0 1 1 0
// 1 0 0 1 0
// 0 0 1 1 0
// 0 1 1 0 1
// 1 0 0 1 0

// Sample Output-2:
// ----------------
// 0

// Explanation:
// ------------
// In the given grid, each 1 is either at the boundary or connceted to 1 at boundary.
// So, number of people cannot be saved are '0'


import java.util.*;
public class ForestPeople{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[][] = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                arr[i][j] = sc.nextInt();
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i==0 || i==n-1 || j==0 || j==n-1){
                    if(arr[i][j] == 1){
                        dfs(arr,i,j,n);
                    }
                }
            }
        }
        int res = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                res+=arr[i][j]==1?1:0;
            }
        }
        System.out.println(res);
        sc.close();
    }
    public static void dfs(int[][] arr,int i,int j,int n){
        if(i==n || i<0 || j==n || j<0 || arr[i][j]==0) return;
        arr[i][j]=0;
        dfs(arr,i,j+1,n);
        dfs(arr,i,j-1,n);
        dfs(arr,i+1,j,n);
        dfs(arr,i-1,j,n);
    }
}