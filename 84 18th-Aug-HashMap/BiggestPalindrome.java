// Mr Gnanesh is working with words. He has given a list of words. 
// Each word in the list contains only two lowercase letters [a-z].

// He wants to create biggest word BW, by concatenating words from the list, which 
// is a palindrome too. He is allowed to use each word from the list atmost once.

// Return the length of the biggest word can be formed using the list.
// If it is impossible to create such word, return 0.

// Input Format:
// -------------
// Space separated strings, words[], two letter words.

// Output Format:
// --------------
// Print an integer result.


// Sample Input-1:
// ---------------
// ab ba dd

// Sample Output-1:
// ----------------
// 6

// Explanation: 
// ------------
// The biggest word is, ab,dd,ba => abddba, which is a palindrome.


// Sample Input-2:
// ---------------
// pq rs sr mk km pq

// Sample Output-2:
// ----------------
// 8

// Explanation: 
// ------------
// The biggest word is, rs,sr,mk,km => rsmkkmsr or mkrssrkm..etc, 
// which is a palindrome.


// Sample Input-3:
// ---------------
// aa bb cc

// Sample Output-3:
// ----------------
// 2

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class BiggestPalindrome{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String arr[] = sc.nextLine().split(" ");
        Map<String,Integer> map = new HashMap<>();
        int res = 0;
        for(String s : arr){
            StringBuilder sb = new StringBuilder(s).reverse();
            if(map.getOrDefault(sb.toString(),0)>0){
                res+=4;
                map.put(sb.toString(),map.get(sb.toString())-1);
            }else{
                map.put(s,map.getOrDefault(s,0)+1);
            }
        }
        for(String s  : map.keySet()){
            if(s.charAt(0)==s.charAt(1)){
                res+=2;
                break;
            }
        }
        System.out.println(res);
        sc.close()
    }
}