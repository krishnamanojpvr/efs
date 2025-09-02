// Mr Saurabh is given a list of words.
// Your task is to help Mr Saurabh to find the size of largest group

// A group is formed using the following rules:
// - Pick one smallest word (in length) and form a group with this word
//   (if it is not part of any other group yet)
// - Add a letter at any place in the word without changing the relative order 
//   of the letters in it, and if it forms a word which is existing in the list[],
//   then add the word to the group.
// - Repeat the above two steps, till all the words in the list are part of groups.

// NOTE:You move form more than one group.

// Input Format:
// -------------
// Space separated words, wordsList[].

// Output Format:
// --------------
// Print an integer result


// Sample Input-1:
// ---------------
// many money n an mony any one mone on

// Sample Output-1:
// ----------------
// 5

// Explanation:
// ------------
// the words group is : [n, on, one, mone, money]


// Sample Input-2:
// ---------------
// a ab abb babb abab baba bab abbaa

// Sample Output-2:
// ----------------
// 4

import java.util.*;
public class Solution{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String arr[] = sc.nextLine().split(" ");
        Arrays.sort(arr,(a,b)->a.length()==b.length()?a.compareTo(b):a.length()-b.length());
        int res =0;
        int n = arr.length;
        for(int i=0;i<n;i++){
            Set<String> set = new HashSet<>();
            StringBuilder sb = new StringBuilder(arr[i]);
            for(int j=0;j<n;j++){
                if(j==i) continue;
                set.add(arr[j]);
            }
            res= Math.max(res, bt(set,sb));
        }
        System.out.println(res);
    }
    public static int bt(Set<String> set, StringBuilder sb){
        int bestLen = 1;
        for(int i=0;i<26;i++){
            int len = sb.length();
            for(int j=0;j<=len;j++){
                sb.insert(j,(char)('a'+i));
                if(set.contains(sb.toString())){
                    bestLen = Math.max(bestLen, 1+bt(set,sb));
                }
                sb.deleteCharAt(j);
            }
        }
        return bestLen;
    }
}

 class Solution2{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String arr[] = sc.nextLine().split(" ");
        Arrays.sort(arr,(a,b)->a.length()==b.length()?a.compareTo(b):a.length()-b.length());
        int res =0;
        int n = arr.length;
        Map<String,Integer> dp = new HashMap<>();
        for(String w : arr){
            int best = 1;
            for(int i=0;i<w.length();i++){
                String prev = w.substring(0,i) + w.substring(i+1);
                if(dp.containsKey(prev)){
                    best =Math.max(best,dp.get(prev)+1);
                }
                dp.put(w,best);
                res = Math.max(res,best);
            }
        }
        System.out.println(res);
    }
    
}