// Imagine you’re decoding a secret message that outlines the hierarchical structure 
// of a covert spy network. The message is a string composed of numbers and parentheses. 
// Here’s how the code works:

// - The string always starts with an agent’s identification number, this is the 
//   leader of the network.
// - After the leader’s ID, there can be zero, one, or two segments enclosed in 
//   parentheses. Each segment represents the complete structure of one subordinate 
//   network.
// - If two subordinate networks are present, the one enclosed in the first (leftmost) 
//   pair of parentheses represents the left branch, and the second (rightmost) 
//   represents the right branch.

// Your mission is to reconstruct the entire spy network hierarchy based on this 
// coded message.

// Example 1:
// Input: 4(2(3)(1))(6(5))
// Output: [4, 2, 6, 3, 1, 5]

// Spy network:
//         4
//        / \
//       2   6
//      / \  /
//     3   1 5

// Explanation:
// Agent 4 is the leader.
// Agent 2 (with its own subordinates 3 and 1) is the left branch.
// Agent 6 (with subordinate 5) is the right branch.

// Example 2:
// Input: 4(2(3)(1))(6(5)(7))
// Output: [4, 2, 6, 3, 1, 5, 7]

// Spy network:
//          4
//        /   \
//       2     6
//      / \   / \
//     3   1 5   7

// Explanation:
// Agent 4 leads the network.
// Agent 2 with subordinates 3 and 1 forms the left branch.
// Agent 6 with subordinates 5 and 7 forms the right branch.

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

public class SpyNetwork {

    public static ArrayList<Integer> levelOrderTraversal(Node root) {
        ArrayList<Integer> res = new ArrayList<>();
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
            res.addAll(temp);
        }
        return res;
    }

    public static Node spyNetwork(String s) {
        Stack<Node> st = new Stack<>();
        int len = s.length();
        int i = 0;
        int num = 0;
        boolean flag = false;
        if (s.charAt(i) == '-') {
            flag = true;
            i++;
        }
        while (i < len && Character.isDigit(s.charAt(i))) {
            num = num * 10 + Integer.parseInt(s.charAt(i) + "");
            i++;
        }
        Node root = new Node(flag ? 0 - num : num);
        st.push(root);
        while (i < len && !st.isEmpty()) {
            if (s.charAt(i) == '(') {
                i++;
                flag = false;
                if (i < len && s.charAt(i) == '-') {
                    flag = true;
                    i++;
                }
                num = 0;
                while (i < len && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + Integer.parseInt(s.charAt(i) + "");
                    i++;
                }
                st.push(flag ? new Node(0 - num) : new Node(num));
            } else if (i < len && s.charAt(i) == ')') {
                Node top = st.pop();
                if (!st.isEmpty()) {
                    Node bottom = st.peek();
                    if (bottom.left == null) {
                        bottom.left = top;
                    } else {
                        bottom.right = top;
                    }
                }
                i++;
            }
        }
        return root;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        Node root = spyNetwork(s);
        System.out.print(levelOrderTraversal(root));
        sc.close();
    }

}
