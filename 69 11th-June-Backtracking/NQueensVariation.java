// A group of students is forming a tech club, and they are 
// interviewing K × K students. Each student has two skill levels:
//  - Mathematics (M)
//  - Physics (P)
// These skill levels range from 0 to K-1.

// They want to form committees of size N under the following 
// conditions:
//  - All members of a committee must have different Mathematics 
//    skill levels.
//  - All members must have different Physics skill levels.
//  - For any two students in the committee, the difference of their 
//    Math skills must not equal the difference of their Physics skills, 
//    i.e., |M1 - M2| != |P1 - P2|

// Input Format:
// -------------
// input1: Integer N – desired committee size
// input2: Integer K – number of skill levels for Math and Physics 
// (total students = K × K)

// Output Fromat:
// --------------
// An integer representing the number of valid committees of size N 
// that can be formed from the K × K students. Return the result 
// modulo 10⁹ + 7.

// Sample Input: 
// -------------
// 2
// 3

// Sample Output: 
// --------------
// 8

// Explanation:
// ------------
// Examples of some valid pairs:
// (0,0) and (1,2) → |0-1| = 1, |0-2| = 2 ✅
// (0,1) and (1,0) → |0-1| = 1, |1-0| = 1 ❌ (same diff → invalid)
// (0,0) and (2,1) → |0-2| = 2, |0-1| = 1 ✅

// 8 Valid Pairs: 1. (0,0) - (1,2)
// 2. (0,0) - (2,1)
// 3. (0,1) - (2,0)
// 4. (0,2) - (1,0)
// 5. (0,2) - (2,1)
// 6. (1,0) - (2,2)
// 7. (1,2) - (0,0)
// 8. (1,2) - (2,0)

// Sample Input: 
// -------------
// 2
// 2

// Sample Output: 
// --------------
// 0

import java.util.*;
public class NQueensVariation{
    public static int count = 0;
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        boolean[][] vis = new boolean[k][k];
        bt(n,k,0,0,vis);
        System.out.println(count);
        sc.close();
    }
    public static void bt(int n,int k,int row,int c,boolean[][] vis){
        if(c==n){
            count+=1;
            return;
        }
        if(row==k) return;
        for(int j=0;j<k;j++){
            if(isValid(row,j,vis,k)){
                vis[row][j] = true;
                bt(n,k,row+1,c+1,vis);
                vis[row][j] = false;
            }
        }
        bt(n,k,row+1,c,vis);
    }
    public static boolean isValid(int row,int col,boolean[][] vis,int k){
        for(int i=0;i<row;i++){
            if(vis[i][col]) return false;
        }
        for(int i=row-1,j=col-1;i>=0 && j>=0;i--,j--){
            if(vis[i][j]) return false;
        }
        for(int i=row-1,j=col+1;i>=0 && j<k;i--,j++){
            if(vis[i][j]) return false;
        }
        return true;
    }
}
        
        
        
        
        
        
        