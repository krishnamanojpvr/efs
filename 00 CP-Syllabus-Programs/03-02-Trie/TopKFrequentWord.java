/*Top K frequent words USING TIRE

We are given an array of strings words and an integer k, we have to return k strings which has highest frequency.
We need to return the answer which should be sorted by the frequency from highest to lowest and the words which has the same frequency sort them by their alphabetical order.
Example-1:
Input:
words = ["i","love","writing","i","love","coding"],
k = 2
Output:
["i","love"]
Explanation: "i" and "love" are the two most frequent words.

Example-2:
Input:
words = ["it","is","raining","it","it","raining","it","is","is"],
 k = 3
Output:
["it","is","raining"]

Approach: 

In this method, we will use Trie data structure and bucket sort

Trie data structure is like a tree which is used to store strings.
It is also known as the digital tree or prefix tree. 
There can be maximum of 26 children in each node of the trie
and the root node is always the null node.

Bucket Sort algorithm is used for sorting elements by arranging them
into multiple groups which are known as buckets. 
These buckets are sorted by using any sorting algorithm and then 
these sorted buckets are combined to form a complete sorted list.

Algorithm:
	We will use unordered map to store the frequency of each word.
	Since, in this question the least frequency of any word can be greater than or equal to one and the maximum frequency is less than or equal to the length of the given vector so we will use bucket sort to store words.
	Then for each bucket we will define a trie to store the words of the same frequency.
	We don't have to sort the words inside each bucket as the words will be arranged in the alphabetical order inside trie.

case =1
input =ball,are,case,doll,egg,case,doll,egg,are,are,egg,case,are,egg,are,case
3
output =[are, case, egg]

case =2
input =ball,are,case,doll,egg,case,doll,egg,are,are,egg,case,are,egg,are
3
output =[are, egg, case]

case =3
input =you,think,i,am,not,there,but,i,am,here,but,you,are,searching,somewhere,but,i,am,not,there
4
output =[am, but, i, not]

case =4
input =the,day,is,sunny,the,the,the,sunny,is,is
4
output =[the, is, sunny, day]

case =5
input =you,think,i,am,not,there,but,i,am,here,but,you,are,searching,somewhere,but,i,am,not,there,the,day,is,sunny,the,the,the,sunny,is,is,ball,are,case,doll,egg,case,doll,egg,are,are,egg,case,are,egg,are,case
5
output =[are, case, egg, the, am]

case =6
input =you,think,i,am,not,there,but,i,am,here,but,you,are,searching,somewhere,but,i,am,not,there,the,day,is,sunny,the,the,the,sunny,is,is,ball,are,case,doll,egg,case,doll,egg,are,are,egg,case,are,egg,are,case
12
output =[are, case, egg, the, am, but, i, is, doll, not, sunny, there]

case =7
input =peter,piper,picked,a,peck,of,pickled,peppers,a,peck,of,pickled,peppers,peter,piper,picked,if,peter,piper,picked,a,peck,of,pickled,peppers,where,is,the,peck,of,pickled,peppers,peter,piper,picked
7
output =[of, peck, peppers, peter, picked, pickled, piper]

case =8
input =peter,piper,picked,a,peck,of,pickled,peppers,a,peck,of,pickled,peppers,peter,piper,picked,if,peter,piper,picked,a,peck,of,pickled,peppers,where,is,the,peck,of,pickled,peppers,peter,piper,picked
9
output =[of, peck, peppers, peter, picked, pickled, piper, a, if]


Approach:
1.Count Frequencies: Use a HashMap<String, Integer> to count the frequency of each word.

2.Insert into Trie: Insert each unique word into a Trie, and store the frequency at the end node of each word.

3.DFS on Trie: Traverse the Trie using DFS to collect words and their frequencies.

4.Use Priority Queue (Min-Heap):

    ->Build a min-heap of size k.

    ->Compare based on:

        -> Higher frequency

        -> If frequency is same → lexicographically smaller first
*/


import java.util.*;

class TrieNode 
{
    TrieNode[] children;
    boolean isEndOfWord;
    int frequency;  // store frequency at end node
    String word;    // store the word itself at end node for reference

    TrieNode() 
    {
        children = new TrieNode[26];
        isEndOfWord = false;
        frequency = 0;
        word = null;
    }
}

class Trie 
{
    TrieNode root;

    Trie() 
    {
        root = new TrieNode();
    }

    public void insert(String word, int freq) 
    {
        TrieNode node = root;
        for (char c : word.toCharArray()) 
        {
            int idx = c - 'a';
            if (node.children[idx] == null)
             {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.isEndOfWord = true;
        node.frequency = freq;
        node.word = word;
    }

    public void collectWords(TrieNode node, PriorityQueue<StringFrequency> pq, int k) 
    {
        if (node == null) return;

        if (node.isEndOfWord)
         {
            pq.offer(new StringFrequency(node.word, node.frequency));
            if (pq.size() > k) {
                pq.poll(); // remove lowest priority element
            }
        }

        for (TrieNode child : node.children)
         {
            if (child != null)
             {
                collectWords(child, pq, k);
            }
        }
    }
}

class StringFrequency 
{
    String word;
    int freq;

    StringFrequency(String word, int freq)
     {
        this.word = word;
        this.freq = freq;
    }
}

// Custom comparator for min-heap
class WordComparator implements Comparator<StringFrequency>
 {
    public int compare(StringFrequency a, StringFrequency b)
     {
        if (a.freq == b.freq) 
        {
            return b.word.compareTo(a.word); // reverse for min-heap
        }
        return Integer.compare(a.freq, b.freq); // min-heap based on frequency
    }
}

public class FrequentWord
 {
    public static List<String> topKFrequent(String[] words, int k)
     {
        // Step 1: Count frequency
        Map<String, Integer> freqMap = new HashMap<>();
        for (String word : words) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }

        // Step 2: Build Trie
        Trie trie = new Trie();
        for (Map.Entry<String, Integer> entry : freqMap.entrySet())
         {
            trie.insert(entry.getKey(), entry.getValue());
        }

        // Step 3: Use PriorityQueue to find top k
        PriorityQueue<StringFrequency> pq = new PriorityQueue<>(new WordComparator());

        // Step 4: DFS to collect words into priority queue
        trie.collectWords(trie.root, pq, k);

        // Step 5: Build result list from heap (reverse order)
        List<String> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(pq.poll().word);
        }
        Collections.reverse(result); // since min-heap gives smallest first

        return result;
    }

    public static void main(String[] args) 
    {
        Scanner sc=new Scanner(System.in);
		String dict[]=sc.nextLine().split(",");
        int k=sc.nextInt();
		System.out.println(new FrequentWord().topKFrequent(dict,k));
    }
}
