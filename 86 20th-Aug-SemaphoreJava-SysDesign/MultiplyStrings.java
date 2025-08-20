// Babylonians invented multiplication of numbers.

// You will be given two strings consist of digits only.
// You have to perform the multiplication of these two strings using 
// Babylonians approach, and return the result of multiplication of two strings.


// Input Format:
// -------------
// Two space separated strings, num1 and num2.

// Output Format:
// --------------
// Print the product of two strings num1 and num2.


// Sample Input-1:
// ---------------
// 5 4 

// Sample Output-1:
// ----------------
// 20


// Sample Input-2:
// ---------------
// 121 432 

// Sample Output-2:
// ----------------
// 52272

// Note:
// -----
// 	- Input should contain digits only, and should not contain leading 0's 
// 	  except number 0.
// 	- Output will be in the range of integer only.
// 	- There will be no leading minus '-' symbol also.
// 	- Should not use built-in BigInteger library.
// 	- Should not convert the given strings into integers.


import java.util.*;
public class MultiplyStrings{
    public static void main(String arg[]){
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String b = sc.next();
        sc.close();
        if("0".equals(a) || "0".equals(b)){
            System.out.println(0);
            return;
        }
        int res[] = new int[a.length()+b.length()-1];
        for(int i=0;i<a.length();i++){
            for(int j=0;j<b.length();j++){
                res[i+j] += (a.charAt(i)-'0') * (b.charAt(j)-'0'); 
            }
        }
        for(int i=res.length-1;i>0;i--){
            res[i-1] += res[i]/10;
            res[i] %= 10;
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i : res){
            sb.append(i);
        }
        System.out.println(sb);
    }
}