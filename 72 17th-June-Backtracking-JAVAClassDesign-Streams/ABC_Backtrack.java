// Ravi is playing with strings.Given Three alphabets [a,b,c] , ravi has to 
// make strings such that no two adjacents alphabets are same.

// For example, He can make strings as "ab", "acb", "b" and "cbabcabcb" which 
// are acceptable.where as the strings "bb", "bcc" and "aabbc" are not acceptable.

// Given two integers N and L, Ravi made a list of all acceptable strings of 
// length N sorted in lexicographical order.

// Return the Lth string of this list or return an empty string if there are less 
// than L acceptable strings of length N.

// Input Format:
// -------------
// Line-1: Two space separated integers N and L.

// Output Format:
// --------------
// Print a string result.


// Sample Input-1:
// ---------------
// 2 3

// Sample Output-1:
// ----------------
// ba

// Explanation:
// -------------
// Strings in lexigraphical order are ab,ac,ba,bc,ca,cb. and 3rd one is ba.


// Sample Input-2:
// ---------------
// 3 4

// Sample Output-2:
// ----------------
// acb

// Explanation:
// ------------
// Strings in lexigraphical order are aba,abc,aca,acb,bab....

import java.util.*;
public class ABC_Backtrack{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int l = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        StringBuilder res = new StringBuilder();
        String abc = "abc";
        bt(n,l,abc,sb,res,new int[]{0},0);
        System.out.println(res);
        sc.close();
    }
    public static void bt(int n,int l,String abc,StringBuilder sb,StringBuilder res,int[] c,int ind){
        if(res.length()>1) return;
        if(ind==n && c[0]==l-1){
            res.setLength(0);
            res.append(sb);
            return;
        }
        else if(ind==n){
            c[0]++;
            return;
        }
        for(int i=0;i<3;i++){
            
            if(sb.length()==0 || (sb.length()>0 && sb.charAt(sb.length()-1)!=abc.charAt(i))){ ;
                sb.append(abc.charAt(i));
                bt(n,l,abc,sb,res,c,ind+1);
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }
}