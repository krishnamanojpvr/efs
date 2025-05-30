// A grid of light bulbs is given, represented as a matrix of size rows x cols, 
// where each cell contains either 0 (off) or 1 (on).

// Your task is to turn all the light bulbs off (0) by following the toggle rule:
//     - In each step, you can choose either an entire row or an entire column 
//     and toggle all its elements (change 0 to 1 and 1 to 0).

// At the end, if all light bulbs are turned off, print true, otherwise print false.

// Input Format
// -------------
// Line-1: Read two integers rows and cols(space separated).
// Line-2: Read the matrix of dimension rows X cols.

// Output Format
// --------------
// Print a boolean result.

// Sample input-1:
// ---------------
// 5 5
// 0 0 1 0 0
// 0 0 1 0 0
// 1 1 0 1 1
// 0 0 1 0 0
// 0 0 1 0 0

// Sample output-1:
// ----------------
// true

// Explanation:
// ------------
// 0 0 1 0 0          0 0 1 0 0           0 0 0 0 0
// 0 0 1 0 0   row-3  0 0 1 0 0   cols-3  0 0 0 0 01 1 0 1 1   --->   0 0 1 0 0   --->    0 0 0 0 0
// 0 0 1 0 0          0 0 1 0 0           0 0 0 0 0
// 0 0 1 0 0          0 0 1 0 0           0 0 0 0 0 

// Sample input-2
// --------------
// 2 2
// 1 1
// 0 1

// Sample output-2
// ---------------
// false

import java.util.*;

public class RemoveAllOnesWithRowAndColumnFlips {

    public static boolean removeOnes(int[][] grid) {
        int row = grid.length; // get dimensions of grid
        int col = grid[0].length;

        for (int c = 0; c < col; c++) { // flip columns so that first row only has 0's
            if (grid[0][c] == 1) {
                for (int r = 0; r < row; r++) { // flips a column
                    grid[r][c] ^= 1;
                }
            }
        }

        for (int r = 1; r < row; r++) { // checks if each row has all 0's or all 1's
            int sum = 0;
            for (int c = 0; c < col; c++) {
                sum += grid[r][c];
            }
            if (sum != 0 && sum != col) {
                return false;
            }

        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int arr[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        System.out.println(removeOnes(arr));
        sc.close();

    }

    // *Intuition : All rows are identical or any row is opposite of rest */

    // *Flip rows if starting of every row in 1 */
    // *Flip columns if starting of every column in 1 */
    // *If we find any '1' after the flipping, return false */
    // *otherwise return true */
    // public static boolean flip(int[][] arr, int n, int m) {
    // for (int i = 0; i < n; i++) {
    // if (arr[i][0] == 1) {
    // for (int j = 0; j < m; j++) {
    // arr[i][j] = 1 - arr[i][j];
    // }
    // }
    // }
    // for (int i = 0; i < m; i++) {
    // if (arr[0][i] == 1) {
    // for (int j = 0; j < n; j++) {
    // arr[j][i] = 1 - arr[j][i];
    // }
    // }
    // }
    // for (int i = 0; i < n; i++) {
    // for (int j = 0; j < m; j++) {
    // if (arr[i][j] == 1) {
    // return false;
    // }
    // }

    // }
    // return true;
    // }
}