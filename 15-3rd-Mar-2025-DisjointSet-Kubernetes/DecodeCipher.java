// In the world of secret codes and cryptography, you are entrusted with deciphering a hidden message. 
// You have two encoded keys, key1 and key2, both of equal length. Each character 
// in key1 is paired with the corresponding character in key2. 

// This relationship follows the standard rules of an equivalence cipher:
// • Self-Mapping: Every character inherently maps to itself.  
// • Mutual Mapping: If a character from key1 maps to one in key2, then that 
//   character in key2 maps back to the one in key1.  
// • Chain Mapping: If character A maps to B, and B maps to C, then A, B, and C 
//   are all interchangeable in this cipher.

// Using this mapping, you must decode a cipherText, by replacing every character 
// with the smallest equivalent character from its equivalence group. 
// The result should be the relatively smallest possible decoded message.


// Input Format:
// -------------
// Three space separated strings, key1 , key2 and cipherText

// Output Format:
// --------------
// Print a string, decoded message which is relatively smallest string of cipherText.

// Example 1: 
// input=
// attitude progress apriori
// output=
// aaogoog


// Explanation: The mapping pairs form groups: [a, p], [o, r, t], [g, i], [e, u], 
// [d, e, s]. By substituting each character in cipherText with the smallest from 
// its group, you decode the message to "aaogoog".


// Constraints:  
// • 1 <= key1.length, key2.length, cipherText.length <= 1000  
// • key1.length == key2.length  
// • key1, key2, and cipherText consist solely of lowercase English letters.

//* Disjoint Set Union Find Problem */
import java.util.*;
public class DecodeCipher{
    public static int[] parentArr(){
        int arr[] = new int[26];
        for(int i=0;i<26;i++){
            arr[i] = i;
        }
        return arr;
    }
    public static String cipher(String[] input){
        String key1 = input[0];
        String key2 = input[1];
        String cipherCode = input[2];
        int[] parent = parentArr();
        for(int i=0;i<key1.length();i++){
            union(parent,key1.charAt(i)-'a',key2.charAt(i)-'a');
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<cipherCode.length();i++){
            int p = find(parent,cipherCode.charAt(i)-'a');
            sb.append((char)(p+97));
        }
        return sb.toString();
    }
    public static void union(int[] parent,int a,int b){
        int x = find(parent,a);
        int y = find(parent,b);
        if(x==y) return;
        else if(x<y) parent[y] = x;
        else parent[x] = y;
        return;
    }
    public static int find(int[] parent,int c){
        if(parent[c]!=c){
            parent[c] = find(parent,parent[c]);
        }
        return parent[c];
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String input[] = sc.nextLine().split(" ");
        System.out.println(cipher(input));
        sc.close();
    }
}