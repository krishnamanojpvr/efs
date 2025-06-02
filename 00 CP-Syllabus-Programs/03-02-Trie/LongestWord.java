/*Longest word with all prefixes: Using Trie

Given an array of strings words, find the longest string in words such that every prefix of 
it is also in words.
For example, let words = ["a", "app", "ap"]. 
The string "app" has prefixes "ap" and "a", all of which are in words.
Return the string described above. If there is more than one string 
with the same length, return the lexicographically smallest one,
and if no string exists, return "".

Example 1:
Input: words = ["k","ki","kir","kira", "kiran"]
Output: "kiran"
Explanation: "kiran" has prefixes "kira", "kir", "ki", and "k", and all of them appear in words.
.
Example 2:
Input: words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
Output: "apple"
Explanation: Both "apple" and "apply" have all their prefixes in words. However, "apple"is lexicographically smaller, so we return that.

Example 3:
Input: words = ["abc", "bc", "ab", "qwe"]
Output:"" 


case =1
input =
k kmi km kmit kme kmec ksj ksjc ks kmecs
output ="kmecs"

case =2
input =t tanker tup tupl tu tuple tupla
output ="tupla"


case =4
input =hyd hydr hydra hydration hydrat hydratio hydrati hyde hydera hyder hy h hyderabad hyderab hyderaba
output ="hyderabad"

case =5
input =hyd hydr hydra hydration hydrat hydratio hydrati hyde hyderb hyder hy h hyderabad hyderab hyderaba
output ="hydration"

case =6
input =hyd hydr hydra hydration hydrat hydratio hydrati hyde hyderb hyder hy h hyderabad hyderab hyderaba hyderguda hyderg hydergud hydergu
output ="hyderguda"

*/
import java.util.*;
                            
class Node
 {
    Node[] links; 
    // Array to hold links to  child nodes for each character
    boolean flag; 
    // Flag to indicate if it  marks the end of a word

    // Constructor to   initialize the node
    public Node() {
        links = new Node[26]; 
        // Initialize array for   links to child nodes
        flag = false; 
        // Initialize flag as false, indicating it's not the end of a word
    }

    // Method to check if the node contains a link for a given character
    public boolean containsKey(char ch) 
    {
        // Check if the link forthe character exists
        return links[ch - 'a'] != null; 
    }

    // Method to get the node corresponding to a given character
    public Node get(char ch) 
    {
         // Return the corresponding child node
        return links[ch - 'a']; 
    }

    // Method to set the link for  a given character to a node
    public void put(char ch, Node node) 
    {
        // Set the link for the character to the provided node
        links[ch - 'a'] = node; 
    }

    // Method to mark the node as the end of a word
    public void setEnd()
     {
        // Set the flag to indicate the end of a word
        flag = true; 
    }

    // Method to check if the node marks the end of a word
    public boolean isEnd() 
    {
        // Return the flag indicating if it's the end of a word
        return flag; 
    }
}

class Trie
 {
     // Root node of the Trie
    private Node root; 

    // Constructor to initialize the Trie
    public Trie() 
    {
        // Create a new root node for the Trie
        root = new Node();
    }

    // Method to insert a word into the Trie
    public void insert(String word)
     {
        Node node = root; 
        // Start traversal from the root node
        for (int i = 0; i < word.length(); i++)
         { 
            // Iterate through each character of the word
            char ch = word.charAt(i);
             // If the character doesn't exist as a child node
            if (!node.containsKey(ch))
             { 
                // Create a new node for the character
                node.put(ch, new Node()); 
            }
            // Move to the next node
            node = node.get(ch); 
        }
         // Mark the last node as the end of the word
        node.setEnd(); 
    }

    // Method to check if all prefixes of a word exist in the Trie
    public boolean checkIfAllPrefixExists(String word)
     {
        Node node = root; 
        // Start traversal from the root node
        boolean flag = true; 
        // Initialize flag as true
        for (int i = 0; i < word.length() && flag; i++) 
        { 
            // Iterate through each character of the word
            char ch = word.charAt(i);
            if (node.containsKey(ch)) 
            { 
                // If the character exists as a child node
                node = node.get(ch); 
                // Move to the next node
                flag = flag && node.isEnd(); 
                // Update flag based on whether it's the end of a word
            } 
            else
             {
                // Return false if the prefix doesn't exist
                return false; 
            }
        }
        // Return true if all prefixes exist
        return flag; 
    }
}

public class LongestWord
{
    // Function to find the longest complete string in a given array of strings
    public static String completeString(int n, String[] a)
     {
        Trie obj = new Trie(); 
        // Create a Trie object

        for (String word : a)

            obj.insert(word); 
            
            // Insert each word into the Trie
        String longest = ""; 
        // Initialize the variable to store the longest complete string
        for (String word : a) 
        { 
            // Iterate through each word in the array
            if (obj.checkIfAllPrefixExists(word))
             { 
                // Check if all prefixes of the word exist
                if (word.length() > longest.length() || (word.length() == longest.length()  && word.compareTo(longest) < 0)) 
                 {
                     // Update the longest string if the current word is longer or lexicographically smaller
                    longest = word;
                }
            }
        }
        // Return "None" if no  complete string found
        if (longest.equals("")) 
        return "None"; 
        // Return the longest complete string
        return longest; 
    }

    public static void main(String[] args)
     {
        
            Scanner sc=new Scanner(System.in);
            String dict[]=sc.nextLine().split(" ");
            System.out.println(completeString(dict.length,dict));
            sc.close();
        }
                  
}
                            
                        