/*Problem Statement: Implement a Trie Data Structure which supports
 the following three operations:

Search (word): To check if the string `word` is present in the Trie or not.
Insert (word): To insert a string `word` in the Trie.
Start With(word): To check if there is a string that has the prefix `word`.

 
Enter strings:
striver striving string strike
Strings are inserted in Trie.
Enter a word to search:
striving
True
Enter a prefix to check:
stri
True
Enter a word to delete:
striving
Deleted successfully

 */


import java.util.*;

 class Trie
  {
    // Node structure for Trie
    static class Node 
    {
        // Array to store links to child nodes,each index represents a letter
       
        Node[] links = new Node[26];
       
        // Flag indicating if the node marks the end of a word
        boolean flag = false;

        // Check if the node contains a specific key (letter)
        boolean containsKey(char ch)
         {
            return links[ch - 'a'] != null;
        }
        
        // Insert a new node with a specific key (letter) into the Trie
        void put(char ch, Node node) 
        {
            links[ch - 'a'] = node;
        }

        // Get the node with a specific key (letter) from the Trie
        Node get(char ch) 
        {
            return links[ch - 'a'];
        }

         // Set the current node as the end of a word
        void setEnd() 
        {
            flag = true;
        }

        void unsetEnd() 
        {
            flag = false;
        }

         // Check if the current node marks the end of a word
        boolean isEnd() {
            return flag;
        }

        boolean isEmpty() 
        {
            for (int i = 0; i < 26; i++) 
            {
                if (links[i] != null) return false;
            }
            return true;
        }
    }

     // Trie class
    private final Node root;

    // Constructor to initialize the Trie with an empty root node
    public Trie() 
    {
        root = new Node();
    }

     // Inserts a word into the Tri Time Complexity O(len), where len is the length of the word
    public void insert(String word) 
    {
        Node node = root;
        for (char ch : word.toCharArray()) 
        {
            if (!node.containsKey(ch)) 
            {
                // Create a new node for the letter if not present
                node.put(ch, new Node());
            }
             // Move to the next node
            node = node.get(ch);
        }
          // Mark the end of the word
        node.setEnd();
    }

    // Returns if the word is in the trie
    public boolean search(String word)
     {
        Node node = root;
        for (char ch : word.toCharArray())
         {
            if (!node.containsKey(ch))
             {
                 // If a letter is not found,the word is not in the Trie
                return false;
            }
             // Move to the next node
            node = node.get(ch);
        }
         // Check if the last node marks the end of a word
        return node.isEnd();
    }

     // Returns if there is any word in the trie that starts with the given prefix
    public boolean startsWith(String prefix)
     {
        Node node = root;
        for (char ch : prefix.toCharArray()) 
        {
            if (!node.containsKey(ch))
             {
                 // If a letter is not found, there is no word with the given prefix
                return false;
            }
             // Move to the next node
            node = node.get(ch);
        }
         // The prefix is found in the Trie
        return true;
    }

      public boolean delete(String word) 
    {
        if (!search(word)) 
        {
            return false; // Word not present in Trie
        }
        deleteHelper(root, word, 0);
        return true; // Word was present and logically deleted
    }

    private boolean deleteHelper(Node current, String word, int index) 
    {
        if (index == word.length()) 
        {
            current.unsetEnd(); // Unset the end of word
            return current.isEmpty(); // If no children, node can be removed
        }

        char ch = word.charAt(index);
        Node next = current.get(ch);
        if (next == null) 
        {
            return false;
        }

        boolean shouldDeleteNextNode = deleteHelper(next, word, index + 1);

        if (shouldDeleteNextNode) //If the child node should be deleted:
        {
            //Remove the reference to that child node (ch) effectively deleting that character’s node from the Trie
            current.links[ch - 'a'] = null;

            //!current.isEnd() → This node is not the end of another word
            //current.isEmpty() → This node has no other children
            //If both are true, it means this node is also useless, and can be deleted too. 
            //So return true to the previous level.

            return !current.isEnd() && current.isEmpty();
        }

        return false;
    }

    // Main method for testing
    public static void main(String[] args) 
    {
        Trie trie = new Trie();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter strings:");
        String[] str = sc.nextLine().split(" ");
        for (String s : str) 
        {
            trie.insert(s);
        }

        System.out.println("Strings are inserted in Trie.");

        // Search
        System.out.println("Enter a word to search:");
        String searchWord = sc.next();
        System.out.println(trie.search(searchWord) ? "True" : "False");

        // Prefix check
        System.out.println("Enter a prefix to check:");
        String prefix = sc.next();
        System.out.println(trie.startsWith(prefix) ? "True" : "False");

        // Delete
        System.out.println("Enter a word to delete:");
        String deleteWord = sc.next();
        boolean deleted = trie.delete(deleteWord);
        System.out.println(deleted ? "Deleted successfully" : "Word not found");

        // Verify deletion
        System.out.println("Searching for word after deletion:");
        System.out.println(trie.search(deleteWord) ? "Still exists" : "Successfully deleted");
    }
}
