// You have given a bulb grid, where the bulb which is turned ON is indicated 
// with 1, and turned OFF is indicated with 0.

// You are allowed to perform an operation:
//     - Select a row/column in the buld grid, and toggle the bulbs,
//     the bulbs which are turned ON will be truned OFF and the bulbs which are 
//     turned OFF will be turned ON.

// Your task is to find the highest possible sum of all the binary equivalents 
// of each row in the bulb grid, after performing the above operation any 
// number of times on the bulb grid.


// Input Format:
// -------------
// Line-1: Two space separated integers, M and N
// Next M lines: N space separated integers, grid[][]

// Output Format:
// --------------
// Print an integer result.


// Sample Input-1:
// ---------------
// 3 5
// 0 1 1 1 1 
// 1 0 1 1 1 
// 0 0 0 0 1 

// Sample Output-1:
// ----------------
// 78

// Explanation:
// ------------
// Given grid is 
// 0 1 1 1 1
// 1 0 1 1 1
// 0 0 0 0 1

// Perform operation on row-0 and row-2
// 1 0 0 0 0
// 1 0 1 1 1
// 1 1 1 1 0

// Perform operation on col-1 and col-4
// 1 1 0 0 1 = 25
// 1 1 1 1 0 = 30
// 1 0 1 1 1 = 23
// So, now the total value of Binary Equivalent is 78


// Sample Input-2:
// ---------------
// 2 3
// 0 1 0
// 0 0 1

// Sample Output-2:
// ----------------
// 11

import java.util.*;
public class ToggleMatrix{
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
        
        for(int i=0;i<m;i++){
            if(arr[i][0]==0){
                for(int j=0;j<n;j++){
                    arr[i][j] ^= 1;
                }
            }
        }
        
        for(int j=0;j<n;j++){
            int count0 = 0;
            int count1 = 0;
            for(int i=0;i<m;i++){
                if(arr[i][j]==0){
                    count0++;
                }
                else{
                    count1++;
                }
            }
            if(count0>count1){
                for(int i=0;i<m;i++){
                    arr[i][j]^=1;
                }   
            }
        }
        
        int sum = 0;
        for(int i=0;i<m;i++){
            StringBuilder sb = new StringBuilder();
            for(int j=0;j<n;j++){
                sb.append(arr[i][j]);
            }
            sum += Integer.parseInt(sb.toString(),2);
        }
        
        System.out.println(sum);
    }
}