// Construct Tree from the given Level-order and In-order.
// Compute the Depth and Sum of the Deepest nodes in the Binary tree

// Input Format:
// -------------
// An integer N representing the number of nodes.
// A space-separated list of N integers representing the in-order traversal.
// A space-separated list of N integers representing the level-order traversal.

// Output Format:
// --------------
// Print two values:
// ->The maximum number of levels.
// ->The sum of all node values at the deepest level.

// Example:
// -------------
// Input:
// 11
// 7 8 4 2 5 9 11 10 1 6 3
// 1 2 3 4 5 6 7 9 8 10 11

// Output:
// 6 11

// Explanation:
// The binary tree structure:
//            1
//          /   \
//        2       3
//       / \     /
//      4   5   6
//     /     \   
//    7       9
//     \       \
//      8      10
//             /
//           11
// Maximum Depth: 6
// nodes at the Deepest Level (6): 11
// Sum of nodes at Level 6: 11

import java.util.*;

class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class InLevelBuildTree {
    public static Node helper(int[] inorder, int[] levelorder, int instart, int inend,
            HashMap<Integer, Integer> levelmap) {
        if (instart > inend) {
            return null;
        }
        int idx = findMinIndex(inorder, levelorder, instart, inend, levelmap);
        Node root = new Node(inorder[idx]);
        if (instart == inend) {
            return root;
        }
        root.left = helper(inorder, levelorder, instart, idx - 1, levelmap);
        root.right = helper(inorder, levelorder, idx + 1, inend, levelmap);
        return root;

    }

    public static Node solve(int[] inorder, int[] levelorder, int n) {
        HashMap<Integer, Integer> levelmap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            levelmap.put(levelorder[i], i);
        }
        return helper(inorder, levelorder, 0, n - 1, levelmap);
    }

    public static int findMinIndex(int[] inorder, int[] levelorder, int instart, int inend,
            HashMap<Integer, Integer> levelmap) {
        int min = Integer.MAX_VALUE;
        int index = 0;
        for (int i = instart; i <= inend; i++) {
            if (levelmap.get(inorder[i]) < min) {
                min = levelmap.get(inorder[i]);
                index = i;
            }
        }
        return index;
    }

    public static TreeMap<Integer, Integer> levelOrderTraversal(Node root) {
        TreeMap<Integer, Integer> levelorder = new TreeMap<>();
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        int level = 1;
        while (!q.isEmpty()) {
            int n = q.size();
            for (int i = 0; i < n; i++) {
                Node node = q.poll();
                levelorder.put(level, levelorder.getOrDefault(level, 0) + node.data);
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
            level++;
        }
        return levelorder;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int inorder[] = new int[n];
        int levelorder[] = new int[n];
        for (int i = 0; i < n; i++) {
            inorder[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            levelorder[i] = sc.nextInt();
        }
        Node root = solve(inorder, levelorder, n);
        TreeMap<Integer, Integer> levels = levelOrderTraversal(root);
        System.out.println(levels.lastKey() + " " + levels.get(levels.lastKey()));
        sc.close();

    }
}