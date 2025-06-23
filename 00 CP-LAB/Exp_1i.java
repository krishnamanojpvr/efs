/*Index Pairs of a String: using TRIE 

Given a text string and words (a list of strings), return all index pairs [i, j] so that the substring text[i]...text[j] is in the list of words.
Example 1:
Input: text = "thestoryofleetcodeandme", 
words = ["story","fleet","leetcode"]
Output: [[3,7],[9,13],[10,17]]

Example 2:	
Input: text = "ababa", words = ["aba","ab"]
Output: [[0,1],[0,2],[2,3],[2,4]]
Explanation: 
Notice that matches can overlap, see "aba" is found in [0,2] and [2,4].



Sample Input-1:
---------------
thekmecandngitcolleges
colleges kmec ngit

Sample Output-1:
----------------
3 6
10 13
14 21


Sample Input-2:
---------------
xyxyx
xyx xy

Sample Output-2:
----------------
0 1
0 2
2 3
2 4


case=1
input=
thestoryofleetcodeandme
story fleet leetcode
output=
[3, 7]
[9, 13]
[10, 17]

case=2
input=
kmitngitkmitngitcbitvbit
kmit ngit it
output=
[0, 3]
[2, 3]
[4, 7]
[6, 7]
[8, 11]
[10, 11]
[12, 15]
[14, 15]
[18, 19]
[22, 23]

case=3
input=
kmecngitkmitarecsecolleges
kmit ngit kmec colleges
output=
[0, 3]
[4, 7]
[8, 11]
[18, 25]

case=4
input=
abcabcabc
abc abca ab
output=
[0, 1]
[0, 2]
[0, 3]
[3, 4]
[3, 5]
[3, 6]
[6, 7]
[6, 8]

case=5
input=
kmecngitkmitarecsecolleges
kmit ngit kmec cse
output=
[0, 3]
[4, 7]
[8, 11]
[15, 17]


*/


import java.util.*;

public class Exp_1i
{

    // Trie Node definition
    static class TrieNode 
    {
        TrieNode[] children = new TrieNode[26];
        boolean isEndOfWord = false;
    }

    // Root of the Trie
    TrieNode root = new TrieNode();

    // Insert a word into the Trie
    private void insert(String word)
     {
        TrieNode node = root;
        for (char c : word.toCharArray()) 
        {
            int idx = c - 'a';
            if (node.children[idx] == null)
                node.children[idx] = new TrieNode();
            node = node.children[idx];
        }
        node.isEndOfWord = true;
    }

    // Main function to get index pairs
    public List<int[]> indexPairs(String text, String[] words)
     {
        // Step 1: Build the Trie
        for (String word : words)
            insert(word);

        List<int[]> result = new ArrayList<>();

        // Step 2: Traverse the text
        for (int i = 0; i < text.length(); i++) 
        {
            TrieNode node = root;
            int j = i;
            while (j < text.length())
             {
                int idx = text.charAt(j) - 'a';

                if (node.children[idx] == null)
                    break;

                node = node.children[idx];

                if (node.isEndOfWord)
                {
                    result.add(new int[]{i, j});
                }
                j++;
            }
        }

        // Step 3: Result is already in sorted order due to traversal logic
        return result;
    }

	public static void main(String args[])
    {
		Scanner sc=new Scanner(System.in);
		String text=sc.nextLine();
		String words[]=sc.nextLine().split(" ");
        IndexPairs solution = new IndexPairs();
		printResult(solution.indexPairs(text,words));
        sc.close();
    }

		private static void printResult(List<int[]> pairs)
         {
            for (int[] pair : pairs) {
                System.out.println(Arrays.toString(pair));
            }
        }
	
}


