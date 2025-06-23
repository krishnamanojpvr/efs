// You will be given the matrix of size R*C,

// You have to return the matrix in special order as shown in the sample tescases.
 

// Input Format:
// -------------
// Line-1 -> Two integers R and C, size of matrix.
// Next R-Lines -> C space separated integers

// Output Format:
// --------------
// Print R-Lines -> C space separated integers, in special order.


// Sample Input-1:
// ---------------
// 3 3
// 1 2 3
// 4 5 6
// 7 8 9

// Sample Output-1:
// ----------------
// 1 2 3
// 6 9 8
// 7 4 5


// Sample Input-2:
// ---------------
// 3 4
// 1 2 3 4
// 5 6 7 8
// 9 10 11 12

// Sample Output-2:
// ----------------
// 1 2 3 4
// 8 12 11 10
// 9 5 6 7


import java.util.*;
public class SpiralMatrix{
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
        List<Integer> res = new ArrayList<>();
        int top = 0,bottom = m-1,left=0,right=n-1;
        while(top<=bottom && left<=right){
            for(int i=left;i<=right;i++){
                res.add(arr[top][i]);
            }
            top++;
            for(int i=top;i<=bottom;i++){
                res.add(arr[i][right]);
            }
            right--;
            if(top<=bottom){
                for(int i = right;i>=left;i--){
                    res.add(arr[bottom][i]);
                }
                bottom--;
            }
            if(left<=right){
                for(int i=bottom;i>=top;i--){
                    res.add(arr[i][left]);
                }
                left++;
            }
        }
        for(int i=0;i<m*n;i+=n){
            for(int j=i;j<i+n;j++){
                System.out.print(res.get(j) + " ");
            }
            System.out.println();
        }
        sc.close();
    }
    
}
