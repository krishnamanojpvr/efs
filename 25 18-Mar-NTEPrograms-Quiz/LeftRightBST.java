// Imagine you are the curator of a historic library, where books are arranged in a 
// unique catalog system based on their publication years. The library’s archive is 
// structured like a hierarchical tree, with each book’s publication year stored at 
// a node. You are given the nodes of this catalog tree starting with main node
// and a list of query years.

// For each query year, you need to find two publication years:
// - The first is the latest year in the archive that is less than or equal to the 
//   query year. If no such book exists, use -1.
// - The second is the earliest year in the archive that is greater than or equal 
//   to the query year. If no such book exists, use -1.

// Display the results as an list of pairs, where each pair corresponds to a query.

// Example 1:
// ----------
// Input: 
// 2006 2002 2013 2001 2004 2009 2015 2014
// 2002 2005 2016

// Output:
// [[2002, 2002], [2004, 2006], [2015, -1]] 

// Archive Structure:
//           2006
//          /    \
//      2002     2013
//      /   \     /   \
//   2001  2004  2009  2015
//                      /
//                   2014

// Explanation:  
// - For the query 2002, the latest publication year that is ≤ 2002 is 2002, and 
//   the earliest publication year that is ≥ 2002 is also 2002.  
// - For the query 2005, the latest publication year that is ≤ 2005 is 2004, and 
//   the earliest publication year that is ≥ 2005 is 2006.  
// - For the query 2016, the latest publication year that is ≤ 2016 is 2015, but 
//   there is no publication year ≥ 2016, so we output -1 for the second value.

// Example 2:
// ----------
// Input:  
// 2004 2009
// 2003

// Output:
// [[-1, 2004]]

// Explanation:  
// - For the query 2003, there is no publication year ≤ 2003, while the earliest 
//   publication year that is ≥ 2003 is 2004.

// Constraints:
// - The total number of books in the archive is in the range [2, 10^5].
// - Each publication year is between 1 and 10^6.
// - The number of queries n is in the range [1, 10^5].
// - Each query year is between 1 and 10^6.

import java.util.*;

class Node {
    int data;
    Node left = null;
    Node right = null;

    Node(int data) {
        this.data = data;
    }
}

public class LeftRightBST {
    public static Node insertBST(Node root, int val) {
        if (root == null) {
            return new Node(val);
        } else if (val > root.data) {
            root.right = insertBST(root.right, val);
        } else {
            root.left = insertBST(root.left, val);
        }
        return root;
    }

    public static Node buildTreeBST(int arr[]) {
        if (arr.length == 0)
            return null;
        Node root = new Node(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            root = insertBST(root, arr[i]);
        }
        return root;
    }

    public static void dfs(Node root, int val, int[] res) {
        if (root == null)
            return;
        if (root.data == val) {
            res[1] = val;
            res[0] = val;
            return;
        }
        if (root.data > val) {
            res[1] = root.data;
            dfs(root.left, val, res);
        } else if (root.data < val) {
            res[0] = root.data;
            dfs(root.right, val, res);
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int arr[] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        int query[] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        Node root = buildTreeBST(arr);
        List<List<Integer>> resal = new ArrayList<>();
        for (int i = 0; i < query.length; i++) {
            int res[] = new int[] { -1, -1 };
            dfs(root, query[i], res);
            resal.add(Arrays.asList(res[0], res[1]));
        }
        System.out.println(resal);
        sc.close();
    }
}