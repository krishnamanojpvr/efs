/*Given a 
binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree. 
According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined 
between two nodes p and q as the lowest node in T that has both p and q as descendants (where we 
allow a node to be a descendant of itself).” 
Given the following binary tree: root = [3,5,1,6,2,0,8,null,null,7,4] 
Example 1: 
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1 
Output: 3 
Explanation: The LCA of nodes 5 and 1 is 3. 
Example 2: 
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4 
Output: 5 
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according 
to the LCA definition. 
Note: 
All of the nodes' values will be unique. 
p and q are different and both values will exist in the binary tree. */

import java.util.*;


class Node {
    int data;
    Node left, right;
    Node(int value) {
        data = value;
    }
}
public class Exp_1j {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] arr = sc.nextLine().split(" ");
        String[] persons = sc.nextLine().split(" ");

        List<Integer> v = new ArrayList<>();
        for (String s : arr) {
            v.add(Integer.parseInt(s));
        }

        int p1 = Integer.parseInt(persons[0]);
        int p2 = Integer.parseInt(persons[1]);

        Node root = buildTree(v);
        Node P1 = findNode(root, p1);
        Node P2 = findNode(root, p2);

        Node res = lowestCommonAncestor(root, P1, P2);
        System.out.println(res.data);
    }

    static Node buildTree(List<Integer> values) {
        if (values.isEmpty()) return null;
        Node root = new Node(values.get(0));
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while (i < values.size()) {
            Node current = q.poll();
            if (values.get(i) != -1) {
                current.left = new Node(values.get(i));
                q.add(current.left);
            }
            i++;
            if (i < values.size() && values.get(i) != -1) {
                current.right = new Node(values.get(i));
                q.add(current.right);
            }
            i++;
        }
        return root;
    }

    static Node findNode(Node root, int val) {
        if (root == null) return null;
        if (root.data == val) return root;
        Node left = findNode(root.left, val);
        if (left != null) return left;
        return findNode(root.right, val);
    }

    static Node lowestCommonAncestor(Node root, Node p, Node q) {
        if (root == null || root == p || root == q) return root;
        Node left = lowestCommonAncestor(root.left, p, q);
        Node right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        return (left != null) ? left : right;
    }
}


