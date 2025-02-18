// Mr. Rakesh is interested in working with Data Structures.

// He has constructed a Binary Tree (BT) and asked his friend 
// Anil to check whether the BT is a self-mirror tree or not.

// Can you help Rakesh determine whether the given BT is a self-mirror tree?
// Return true if it is a self-mirror tree; otherwise, return false.

// Note:
// ------
// In the tree, '-1' indicates an empty (null) node.

// Input Format:
// -------------
// A single line of space separated integers, values at the treenode

// Output Format:
// --------------
// Print a boolean value.


// Sample Input-1:
// ---------------
// 2 1 1 2 3 3 2

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// 2 1 1 -1 3 -1 3

// Sample Output-2:
// ----------------
// false

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

public class IsSymmetric{
    public static boolean helper(Node left,Node right){
        if(left==null || right==null) return left==right;
        if(left.data!=right.data) return false;
        return helper(left.left,right.right)&&helper(left.right,right.left);
        
    }
    public static boolean isSymmetric(Node root){
        if(root==null){
            return true;
        }
        return helper(root.left,root.right);
    }
    public static Node insert(int[] arr)
    { 
       int n = arr.length;
       Node root = new Node(arr[0]);
       Queue<Node> q = new LinkedList<>();
       q.offer(root);
       int ind =1;
       while(ind<n && !q.isEmpty()){
           Node temp = q.poll();
           if(arr[ind]!=-1){
               temp.left = new Node(arr[ind]);
               q.add(temp.left);
           }
           ind++;
           if(ind<n && arr[ind]!=-1){
               temp.right = new Node(arr[ind]);
               q.add(temp.right);
           }
           ind++;
       }
       return root;
    }
    public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		int arr[] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
		Node root = insert(arr);
		System.out.println(isSymmetric(root));
        sc.close();
	}
}
