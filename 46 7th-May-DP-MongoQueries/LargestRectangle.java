// A group of researchers is analyzing satellite imagery of agricultural fields, 
// represented by a grid of land sections. Each section is marked either as 
// fertile (1) or infertile (0). To efficiently plan crop planting, the researchers 
// need to identify the largest rectangular area consisting exclusively of fertile 
// land sections.

// Given a binary grid representing the land (1 for fertile and 0 for infertile), 
// your task is to compute the area of the largest rectangle consisting entirely 
// of fertile land sections.

// Input Format:
// -------------
// Line-1: Two integers rows(r) and columns(c) of grid.
// Next r lines: c space separated integers, each row of the grid.

// Output Format:
// --------------
// Print an integer, area of the largest rectangle consisting entirely of fertile land sections.

// Example:
// --------
// input=
// 4 5
// 1 0 1 0 0
// 1 0 1 1 1
// 1 1 1 1 1
// 1 0 0 1 0

// output=
// 6

import java.util.*;
public class LargestRectangle{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int arr[][] = new int [m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                arr[i][j] = sc.nextInt();
            }
        }
        int pref[][] = new int[m+1][n];
        for(int i=1;i<=m;i++){
            for(int j=0;j<n;j++){
                pref[i][j] += pref[i-1][j] + arr[i-1][j];
            }
        }
        int res = 0;
        for(int i=1;i<=m;i++){
            for(int j=i;j<=m;j++){
                int count = 0,max = 0;
                for(int k=0;k<n;k++){
                    if(pref[j][k]-pref[i-1][k]==(j-i+1)){
                        count++;
                    }
                    else{
                        count = 0;
                    }
                    max = Math.max(max,count);
                }
                res = Math.max(res,max*(j-i+1));
            }
        }
        System.out.println(res);
        sc.close();
    }
}