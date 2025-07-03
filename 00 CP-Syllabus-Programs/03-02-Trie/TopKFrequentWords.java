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

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class Node {

    public char c;
    public boolean isWord;
    public int count;
    public Node[] children;
    public String str;

    public Node(char c) {
        this.c = c;
        this.isWord = false;
        this.count = 0;
        children = new Node[26];
        str = "";
    }
}

class Trie {

    public Node root;

    public Trie() {
        this.root = new Node('\0');
    }

    public void insert(String word) {
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new Node(c);
            }
            curr = curr.children[c - 'a'];
        }
        curr.isWord = true;
        curr.count += 1;
        curr.str = word;

    }

    public void traverse(Node root, PriorityQueue<Node> pq) {
        if (root.isWord == true) {
            pq.offer(root);
        }
        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null) {
                traverse(root.children[i], pq);
            }
        }
    }
}

public class TopKFrequentWords {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line1 = sc.nextLine();
        int p = sc.nextInt();
        String[] words = line1.split(",");
        Trie t = new Trie();
        PriorityQueue<Node> pq = new PriorityQueue<>(
                (a, b) -> {
                    if (a.count != b.count) {
                        return b.count - a.count;
                    }

                    return a.str.compareTo(b.str);
                }
        );

        for(int i = 0; i < words.length; i++) {
            t.insert(words[i]);
        }
        t.traverse(t.root, pq);

        List<String> res = new ArrayList<>();
        int k = 0;
        while (k++ < p) {
            res.add(pq.poll().str);
        }

        System.out.println(res);
        sc.close();
    }
}
