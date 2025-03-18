// You are a gardener designing a beautiful floral pathway in a vast botanical 
// garden. The garden is currently overgrown with plants, trees, and bushes 
// arranged in a complex branching structure, much like a binary tree. Your task 
// is to carefully prune and rearrange the plants to form a single-file walking 
// path that visitors can follow effortlessly.

// To accomplish this, you must flatten the garden’s layout into a linear sequence 
// while following these rules:
//     1. The garden path should maintain the same PlantNode structure, 
//        where the right branch connects to the next plant in the sequence, 
//        and the left branch is always trimmed (set to null).
//     2. The plants in the final garden path should follow the same arrangement 
//        as a pre-order traversal of the original garden layout. 

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print the list.

// Sample Input:
// -------------
// 1 2 5 3 4 -1 6

// Sample Output:
// --------------
// 1 2 3 4 5 6

// Explanation:
// ------------
// input structure:
//        1
//       / \
//      2   5
//     / \    \
//    3   4    6

// output structure:
// 	1
// 	 \
// 	  2
// 	   \
// 		3
// 		 \
// 		  4
// 		   \
// 			5
// 			 \
// 			  6

import java.util.*;

class BinaryTreeNode {
    public int data = -1;
    public BinaryTreeNode left, right;

    public BinaryTreeNode(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

public class BinaryTreeToLinkedList {
    public static BinaryTreeNode construct(int[] arr) {
        int n = arr.length;
        BinaryTreeNode root = new BinaryTreeNode(arr[0]);
        Queue<BinaryTreeNode> q = new LinkedList<>();
        q.offer(root);
        int ind = 1;
        while (ind < n && !q.isEmpty()) {
            BinaryTreeNode temp = q.poll();
            if (arr[ind] != -1) {
                temp.left = new BinaryTreeNode(arr[ind]);
                q.add(temp.left);
            }
            ind++;
            if (ind < n && arr[ind] != -1) {
                temp.right = new BinaryTreeNode(arr[ind]);
                q.add(temp.right);
            }
            ind++;
        }
        return root;
    }

    public static BinaryTreeNode treeToLl(BinaryTreeNode root) {
        if (root == null || root.left == null)
            return root;
        BinaryTreeNode left = treeToLl(root.left);
        BinaryTreeNode right = treeToLl(root.right);
        if (left == null)
            return root;
        if (right == null) {
            root.right = left;
            root.left = null;
            return root;
        }
        root.right = left;
        root.left = null;
        BinaryTreeNode curr = root;
        while (curr.right != null)
            curr = curr.right;
        curr.right = right;
        return root;
    }

    // Time = O(N) because we traverse the whole binary tree
    // space = O(N) because for a only left child binary tree. The call stack is
    // O(N)
    // class Solution {
    // private TreeNode head;
    // public void treeToLl(TreeNode root) {
    // if (root == null) return;
    // // postorder
    // treeToLl(root.right);
    // treeToLl(root.left);
    // root.right = head;
    // root.left = null;
    // head = root;
    // }
    // }

    public static BinaryTreeNode treeToLlStack(BinaryTreeNode root) {
        Stack<BinaryTreeNode> st = new Stack<>();
        st.push(root);
        while (!st.isEmpty()) {
            BinaryTreeNode node = st.pop();
            if (node.right != null)
                st.push(node.right);
            if (node.left != null)
                st.push(node.left);
            node.right = st.isEmpty() ? null : st.peek();
            node.left = null;
        }
        return root;
    }

    public static void print(BinaryTreeNode root) {
        if (root == null)
            return;
        System.out.print(root.data + " ");
        print(root.right);
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int arr[] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        BinaryTreeNode root = construct(arr);
        // root = treeToLl(root);
        root = treeToLlStack(root);
        print(root);
        sc.close();
    }
}
