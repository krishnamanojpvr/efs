
// There are a series of balloons, wehre few balloons are colored blue(indicated by 1) 
// and others are colored white(indicated by 0).

// Return the count of sub-series of balloon which are colored blue.

// for example:
// 1101 -> sub-series are 1,1,1(subseries of length-1),11(sub-series of length-2).
// Total=4

// Since the answer may be too large, return it modulo 10^9 + 7.

// Note: input is given as a string.
 
// Input Format:
// -------------
// A string represents the status of series of balloons.

// Output Format:
// --------------
// Print an integer

// Sample Input-1:
// ---------------
// 11101

// Sample Output-1:
// ----------------
// 7

// Explanation:
// ------------
// subseries are 1,1,1,1,11,11,111.


// Sample Input-2:
// ---------------
// 101

// Sample Output-2:
// ----------------
// 2

// Explanation:
// -------------
// sub-series are: 1,1.

import java.util.*;
public class Balloon{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        char s[] = sc.next().toCharArray();
        int res = 0;
        int count=0;
        for(char k : s){
            if(k=='1'){
                count++;
            }
            else{
                res+=(((count)*(count+1))/2);
                count=0;
            }
        }
        res+=(((count)*(count+1))/2);
        System.out.println(res);
    }
}