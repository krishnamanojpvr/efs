import java.util.*;

class SuffixTrie 
{	
	static final int NUM_CHARS = 26;
	// SuffixTrie node
	static class SuffixTrieNode
	{
		SuffixTrieNode[] children = new SuffixTrieNode[NUM_CHARS];
	
		// isEndOfWord is true if the node represents end of a word
		boolean isEndOfWord;
		
		SuffixTrieNode()
		{
			isEndOfWord = false;
			for (int i = 0; i < NUM_CHARS; i++)
				children[i] = null;
		}
	};
	
	static SuffixTrieNode root;
	
	// If not present, inserts word into SuffixTrie
	// If the word is prefix of SuffixTrie node, just marks leaf node
	static void insert(String word)
	{
		System.out.println("word " + word);
		int level;
		int length = word.length();
		int index;
	
		SuffixTrieNode currentNode = root;
	
		for (level = 0; level < length; level++)
		{
			index = word.charAt(level) - 'a';
			if (currentNode.children[index] == null)
				currentNode.children[index] = new SuffixTrieNode();
	
			currentNode = currentNode.children[index];
		}
	
		// mark last node as leaf
		currentNode.isEndOfWord = true;
	}
	
	// To check if current node is leaf node or not
	static boolean isLeafNode(SuffixTrieNode root) 
	{
		return root.isEndOfWord == true;
	}
 
	// print SuffixTrie
	static void print(SuffixTrieNode root, char[] str, int level) 
	{
		// If node is leaf node, it indicates end of string, 
		// so a null character is added and string is printed
		if (isLeafNode(root)) 
		{
			for (int k = level; k < str.length; k++)
				str[k] = 0;
			System.out.println(str);
		}
	 
		int i;
		for (i = 0; i < NUM_CHARS; i++) 
		{
			// if NON NULL child is found add parent key to str and
			// call the print function recursively for child node
			if (root.children[i] != null) 
			{
				str[level] = (char) (i + 'a');
				print(root.children[i], str, level + 1);
			}
		}
	}

	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter any string to construct suffix tree");
		String word=sc.nextLine();
		root = new SuffixTrieNode();

		for (int i = 0; i < word.length(); i++)
			insert(word.substring(i));

		char[] str = new char[50];
		print(root, str, 0);
		sc.close();
	}	
}