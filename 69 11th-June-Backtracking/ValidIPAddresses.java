// Pramod is planning to design a program, which helps to create 
// the IP addresses posssible from a given string S, 
// where each IP address should be valid.

// It is guaranteed that S contains only digits.

// Can you help pramod in designing such program, which returns all possible 
// IP addresses. Print the answer in lexicographic order.

// NOTE:
// -----

// - A valid IP address consists of exactly four integers, 
// each integer is between 0 and 255, separated by single dots 
// and cannot have leading zeros
// - IP Addresses are said to be valid if it falls in the range 
// from 0.0.0.0 to 255.255.255.255

// - IP addresses like [123.012.234.255 , 123.234.345.34] are invalid.

// Input Format:
// -------------
// A string S, contains only digits [0-9].

// Output Format:
// --------------
// Print all possible IP addresses which are valid.


// Sample Input-1:
// ---------------
// 23323311123

// Sample Output-1:
// ----------------
// [233.233.11.123, 233.233.111.23]


// Sample Input-2:
// ---------------
// 12345678

// Sample Output-2:
// ----------------
// [1.234.56.78, 12.34.56.78, 123.4.56.78, 123.45.6.78, 123.45.67.8]


// Sample Input-3:
// ---------------
// 02550255

// Sample Output-3:
// ----------------
// [0.25.50.255, 0.255.0.255]

import java.util.*;
public class ValidIPAddresses{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        List<String> res = new ArrayList<>();
        bt(s,res,new StringBuilder(),0,0);
        System.out.println(res);
    }
    public static void bt(String s,List<String> res,StringBuilder sb,int ind,int count){
        if(count==4 && ind==s.length()){
            res.add(sb.toString().substring(0,sb.length()-1));
            return;
        }
        if(count>4 || ind>=s.length()) return;
        
        for(int i=1;i<=3;i++){
            String temp = s.substring(ind,Math.min(ind+i,s.length()));
            int k = Integer.parseInt(temp.toString());
            if(k<0 || k>255 || (temp.startsWith("0") && temp.length()>1)) continue;
            int len = sb.length();
            sb.append(temp.toString() + ".");
            bt(s,res,sb,ind+i,count+1);
            sb.setLength(len);
        }
        sc.close();
    }
}