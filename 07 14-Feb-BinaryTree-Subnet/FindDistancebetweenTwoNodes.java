// Bubloo is working with computer networks, where servers are connected 
// in a hierarchical structure, represented as a Binary Tree. Each server (node) 
// is uniquely identified by an integer value.

// Bubloo has been assigned an important task: find the shortest communication 
// path (in terms of network hops) between two specific servers in the network.

// Network Structure:
// ------------------
// The network of servers follows a binary tree topology.
// Each server (node) has a unique identifier (integer).
// If a server does not exist at a certain position, it is represented as '-1' (NULL).

// Given the root of the network tree, and two specific server IDs (E1 & E2), 
// determine the minimum number of network hops (edges) required to 
// communicate between these two servers.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print an integer value.


// Sample Input-1:
// ---------------
// 1 2 4 3 5 6 7 8 9 10 11 12
// 4 8

// Sample Output-1:
// ----------------
// 4

// Explanation:
// ------------
// The edegs between 4 and 8 are: [4,1], [1,2], [2,3], [3,8]


// Sample Input-2:
// ---------------
// 1 2 4 3 5 6 7 8 9 10 11 12
// 6 6

// Sample Output-2:
// ----------------
// 0

// Explanation:
// ------------
// No edegs between 6 and 6.

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

public class FindDistancebetweenTwoNodes{
    public static Node LCA(Node root,int l,int r){
        if(root==null || root.data==l || root.data==r){
            return root;
        }
        Node leftNode = LCA(root.left,l,r);
        Node rightNode = LCA(root.right,l,r);
        if(leftNode!=null && rightNode!=null){
            return root;
        }
        if(leftNode!=null) return leftNode;
        return rightNode;
        
    }
    public static int helper(Node root,int lr,int depth){
        if(root==null){
            return 0;
        }
        if(root.data==lr) return depth;
        return Math.max(helper(root.left,lr,depth+1),helper(root.right,lr,depth+1));
    }
    public static int distance(Node root,int l,int r){
        if(root==null) return -1;
        Node lca = LCA(root,l,r);
        int ld = helper(lca,l,0);
        int rd = helper(lca,r,0);
        return ld+rd;
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
		int l = sc.nextInt();
		int r = sc.nextInt();
		Node root = insert(arr);
		System.out.println(distance(root,l,r));
        sc.close();
	}
}
