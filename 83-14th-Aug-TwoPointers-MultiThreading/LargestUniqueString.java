// Venkat wants to create a unique name for his child, to do that he is referring 
// two names, one is traditonal name(TN) and other is modern name(MN).

// He is planning to create unique name(UN), using the following ways:
//     - if traditional name TN is non empty, then add the first letter L of TN 
//     to unique name UN and remove the letter L from traditional name TN
// 	e.g., if TN = "havi" and UN="anvika", then after adding L to UN and remove L 
// 	from TN, the names updated as UN="hanvika", TN="avi".
   
//    - if modern name MN is non empty, then add the first letter L of MN 
//     to unique name UN and remove the letter L from modern name MN
//     e.g., if MN = "ram" and UN="ao", then after adding L to UN and remove L 
// 	from MN, the names updated as UN="rao", MN="am".
	
// You are given two names, TN and MN, 
// Your task is to help venkat to generate the unique name for his child,
// and the name should be largest in the dictionary order.

// For example, "kmit" is larer than "kmec", as third letter is greater in "kmit".

// Input Format:
// -------------
// Two space separated names, TN and MN.

// Output Format:
// --------------
// Print a string, the laregst unique name possible.


// Sample Input-1:
// ---------------
// sudha vivid

// Sample Output-1:
// ----------------
// vsuividhda


// Sample Input-2:
// ---------------
// appa banana

// Sample Output-2:
// ----------------
// bappananaa

import java.util.Scanner;

public class LargestUniqueString {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String t = sc.next();
        String m = sc.next();
        int p=0,q=0;
        StringBuilder res = new StringBuilder();
        while(p<t.length() && q<m.length()){
            char c1 = t.charAt(p);
            char c2 = m.charAt(q);
            if(c1>c2){
                res.append(c1);
                p++;
            }
            else if(c2>c1){
                res.append(c2);
                q++;
            }
            else{
                if(t.substring(p).compareTo(m.substring(q))>=0){
                    res.append(c1);
                    p++;
                }
                else{
                    res.append(c2);
                    q++;
                }
            }
        }
        if(p>=t.length()){
            res.append(m.substring(q));
        }
        else if(q>=m.length()){
            res.append(t.substring(p));
        }
        System.out.println(res.toString());
        sc.close();
    }

    
}
