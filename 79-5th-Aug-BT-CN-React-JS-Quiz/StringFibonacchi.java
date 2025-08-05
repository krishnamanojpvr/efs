// Pramod is working on Strings consist of digits only. He wants to findout, 
// whether the given string can form Fibonacci sequence or not.

// A String can form a Fibonacci Sequence, if it contains at least 
// three numbers, and numbers are in the following order:
// first, second, third  = first + second, fourth = third + second, .. so on.

// Return true, if the given string can form fibonacci sequence,
// otherwise, return false.

// Note: Numbers in the fibonacci sequence contains no leading 0's.
// for example, 2, 03,5 or 2,3,05 or 02,3,5 are not valid.

// Input Format:
// -------------
// A String consist of digits only

// Output Format:
// --------------
// Print a boolean value as result.


// Sample Input-1:
// ---------------
// 23581321

// Sample Output-1:
// ----------------
// true

// Explanation: 
// ------------
// Fibonacci Sequence is : 2, 3, 5, 8, 13, 21
// 2, 3, 2+3=5, 3+5=8, 5+8=13, 8+13=21.

// Sample Input-2:
// ---------------
// 199100199

// Sample Output-2:
// ----------------
// true

// Explanation: 
// ------------
// Fibonacci Sequence is : 1 99 100 199
// 1, 99, 1+99=100, 99+100=199.

import java.util.*;
public class StringFibonacchi{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        // did not get 100/100 score
        boolean flag = false;
        OUTER:for(int i=0;i<s.length();i++){
            if(checkLeadingZero(s.substring(0,i+1))){
                continue;
            }
            long x = Long.parseLong(s.substring(0,i+1));
            for(int j=i+1;j<s.length();j++){
                if(checkLeadingZero(s.substring(i+1,j+1))){
                    continue;
                }
                long y = Long.parseLong(s.substring(i+1,j+1));
                // System.out.println("Pair"+ x+" "+y);
                if(y<x){
                    continue;
                }
                boolean temp = bt(s,x,y,j+1);
                if(temp){
                    // System.out.println("BT Res "+ temp);
                    flag = true;
                    break OUTER;
                }
            }
        }
        System.out.println(flag);
        sc.close();
    }
    public static boolean checkLeadingZero(String x){
        return x.charAt(0)=='0';
    }
    public static boolean bt(String s,long x,long y,int curr){
        for(int i=curr;i<s.length();i++){
            if(checkLeadingZero(s.substring(curr,i+1))){
                break;
            }
            if(x+y==Long.parseLong(s.substring(curr,i+1))){
                // System.out.println("Result" + s.substring(curr,i+1));
                if(i+1==s.length()) return true;
                return bt(s,y,x+y,i+1);
                
            }
        }
        return false;
    }
}
