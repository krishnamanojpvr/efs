
/*
You are given two strings of the same length s1 and s2 and a string baseStr. 
We say s1[i] and s2[i] are equivalent characters. 
For example, if s1 = "abc" and s2 = "cde", then we have 'a' == 'c', 'b' == 'd', and 'c' == 'e'. 
Equivalent characters follow the usual rules of any equivalence relation: 

Reflexivity: 'a' == 'a'. 
Symmetry: 'a' == 'b' implies 'b' == 'a'. 
Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' == 'c'. 

For example, given the equivalency information from s1 = "abc" and s2 = "cde", "acd" 
and "aab"  
are equivalent strings of baseStr = "eed", and "aab" is the lexicographically smallest 
equivalent string of baseStr. 
Return the lexicographically smallest equivalent string of baseStr by using the 
equivalency information from s1 and s2. 

Example 1: 
Input: s1 = "parker", s2 = "morris", baseStr = "parser" 
Output: "makkek" 
Explanation: Based on the equivalency information in s1 and s2, we can group their 
characters as [m,p], [a,o], [k,r,s], [e,i]. 
The characters in each group are equivalent and sorted in lexicographical order. 
So the answer is "makkek". 

Example 2: 
Input: s1 = "hello", s2 = "world", baseStr = "hold" 
Output: "hdld" 
Explanation: Based on the equivalency information in s1 and s2, we can group their 
characters as [h,w], [d,e,o], [l,r]. 
So only the second letter 'o' in baseStr is changed to 'd', the answer is "hdld". 

Example 3: 
Input: s1 = "leetcode", s2 = "programs", baseStr = "sourcecode" 
Output: "aauaaaaada" 
Explanation: We group the equivalent characters in s1 and s2 as [a,o,e,r,s,c], [l,p], [g,t]  
and [d,m], thus all letters in baseStr except 'u' and 'd' are transformed to 'a', 
the answer is "aauaaaaada". 
 */
import java.util.*;

public class LexicographicallySmallestEquivalentString {
    public static int[] parentArr() {
        int arr[] = new int[26];
        for (int i = 0; i < 26; i++) {
            arr[i] = i;
        }
        return arr;
    }

    public static String lexicographicallySmallest(String[] input) {
        String s1 = input[0];
        String s2 = input[1];
        String baseStr = input[2];
        int[] parent = parentArr();
        for (int i = 0; i < s1.length(); i++) {
            union(parent, s1.charAt(i) - 'a', s2.charAt(i) - 'a');
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < baseStr.length(); i++) {
            int p = find(parent, baseStr.charAt(i) - 'a');
            sb.append((char) (p + 97));
        }
        return sb.toString();
    }

    public static void union(int[] parent, int a, int b) {
        int x = find(parent, a);
        int y = find(parent, b);
        if (x == y)
            return;
        else if (x < y)
            parent[y] = x;
        else
            parent[x] = y;
        return;
    }

    public static int find(int[] parent, int c) {
        if (parent[c] != c) {
            parent[c] = find(parent, parent[c]);
        }
        return parent[c];
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String input[] = sc.nextLine().split(" ");
        System.out.println(lexicographicallySmallest(input));
        sc.close();
    }
}
