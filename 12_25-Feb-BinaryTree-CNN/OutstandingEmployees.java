// Imagine a company where each employee has a performance score, and 
// the organizational chart is structured as a binary tree with the CEO at the top. 
// An employee is considered "outstanding" if, along the chain of command from the 
// CEO down to that employee, no one has a performance score higher than that 
// employee's score. Your task is to determine the total number of outstanding 
// employees in the company.

// Example 1:
// Input: 3 1 4 3 -1 1 5
// Output: 4

// Chart formed:
//          3
//         / \
//        1   4
//       /   / \
//      3   1   5

// Explanation:
// - The CEO (score 3) is automatically outstanding.
// - The employee with score 4, whose chain is [3,4], is outstanding because 4 
//   is higher than 3.
// - The employee with score 5, following the path [3,4,5], is outstanding as 5 
//   is the highest so far.
// - The subordinate with score 3, along the path [3,1,3], is outstanding because 
//   none of the managers in that chain have a score exceeding 3.

// Example 2:
// Input: 3 3 -1 4 2
// Output: 3

// Chart formed:
//        3
//       / 
//      3
//     / \
//    4   2

// Explanation:
// - The CEO (score 3) is outstanding.
// - The subordinate with score 3 (chain: [3,3]) is outstanding.
// - The employee with score 2 (chain: [3,3,2]) is not outstanding because the 
//   managers have higher scores.

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

public class OutstandingEmployees {
    static int res = 0;

    public static void helper(Node root, int val) {
        if (root == null)
            return;
        if (root.data >= val) {
            val = root.data;
            res++;
        }
        helper(root.left, val);
        helper(root.right, val);
    }

    public static int outstanding(Node root) {
        if (root == null)
            return 0;
        res++;
        helper(root.left, root.data);
        helper(root.right, root.data);
        return res;
    }

    public static Node insert(int[] arr) {
        int n = arr.length;
        Node root = new Node(arr[0]);
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        int ind = 1;
        while (ind < n && !q.isEmpty()) {
            Node temp = q.poll();
            if (arr[ind] != -1) {
                temp.left = new Node(arr[ind]);
                q.add(temp.left);
            }
            ind++;
            if (ind < n && arr[ind] != -1) {
                temp.right = new Node(arr[ind]);
                q.add(temp.right);
            }
            ind++;
        }
        return root;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int arr[] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        Node root = insert(arr);
        System.out.print(outstanding(root));
        sc.close();
    }
}
