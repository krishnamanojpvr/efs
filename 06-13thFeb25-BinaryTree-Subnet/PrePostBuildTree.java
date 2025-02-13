// Given the preorder and postorder traversals of a binary tree, construct 
// the original binary tree and print its level order traversal.

// Input Format:
// ---------------
// Space separated integers, pre order data
// Space separated integers, post order data

// Output Format:
// -----------------
// Print the level-order data of the tree.


// Sample Input:
// ----------------
// 1 2 4 5 3 6 7
// 4 5 2 6 7 3 1

// Sample Output:
// ----------------
// [[1], [2, 3], [4, 5, 6, 7]]

// Explanation:
// --------------
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7


// Sample Input:
// ----------------
// 1 2 3
// 2 3 1

// Sample Output:
// ----------------
// [[1], [2, 3]]

// Explanation:
// --------------
//     1
//    / \
//   2  3

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

public class PrePostBuildTree {
    public static Node helper(int[] preorder, int[] postorder, int prestart, int preend,int poststart, HashMap<Integer, Integer> postmap) {
        if (prestart > preend) {
            return null;
        }
        Node root = new Node(preorder[prestart]);
        if (prestart == preend) {
            return root;
        }
        int leftroot = preorder[prestart+1];
        int leftind = postmap.get(leftroot);
        int leftsubtreesize = leftind-poststart+1;
        
        root.left = helper(preorder, postorder, prestart+1, prestart+leftsubtreesize, poststart, postmap);
        root.right = helper(preorder, postorder, prestart+leftsubtreesize+1, preend, leftind+1 ,postmap);
        return root;

    }

    public static Node solve(int[] preorder, int[] postorder) {
        HashMap<Integer, Integer> postmap = new HashMap<>();
        for (int i = 0; i < postorder.length; i++) {
            postmap.put(postorder[i], i);
        }
        return helper(preorder, postorder, 0, postorder.length - 1,0, postmap);
    }


    public static ArrayList<ArrayList<Integer>> levelOrderTraversal(Node root) {
        ArrayList<ArrayList<Integer>> levelorder = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int n = q.size();
            ArrayList<Integer> temp = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                Node node = q.poll();
                temp.add(node.data);
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
            levelorder.add(temp);
        }
        return levelorder;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int preorder[] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        int postorder[] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        
        Node root = solve(preorder, postorder);
        System.out.println(levelOrderTraversal(root));
        sc.close();

    }
}