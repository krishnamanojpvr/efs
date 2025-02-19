// Balbir Singh is working with networked systems, where servers are connected 
// in a hierarchical structure, represented as a Binary Tree. Each server (node) has 
// a certain processing load (integer value).

// Balbir has been given a critical task: split the network into two balanced 
// sub-networks by removing only one connection (edge). The total processing 
// load in both resulting sub-networks must be equal after the split.

// Network Structure:
// - The network of servers follows a binary tree structure.
// - Each server is represented by an integer value, indicating its processing load.
// - If a server does not exist at a particular position, it is represented as '-1' (NULL).
	
// Help Balbir Singh determine if it is possible to split the network into two equal 
// processing load sub-networks by removing exactly one connection.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print a boolean value.


// Sample Input-1:
// ---------------
// 1 2 3 5 -1 -1 5

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// 3 2 4 3 2 -1 7

// Sample Output-2:
// ----------------
// false

import java.util.*;

class BinaryTreeNode
{
	public int data = -1; 
	public BinaryTreeNode left, right; 
	public BinaryTreeNode(int data)
	{
		this.data = data; 
		left = null; 
		right = null; 
	}
}


public class EqualTree 
{
	public static BinaryTreeNode construct(int[] arr)
    { 
       int n = arr.length;
       BinaryTreeNode root = new BinaryTreeNode(arr[0]);
       Queue<BinaryTreeNode> q = new LinkedList<>();
       q.offer(root);
       int ind =1;
       while(ind<n && !q.isEmpty()){
           BinaryTreeNode temp = q.poll();
           if(arr[ind]!=-1){
               temp.left = new BinaryTreeNode(arr[ind]);
               q.add(temp.left);
           }
           ind++;
           if(ind<n && arr[ind]!=-1){
               temp.right = new BinaryTreeNode(arr[ind]);
               q.add(temp.right);
           }
           ind++;
       }
       return root;
    }
    
    public static boolean isEqual(BinaryTreeNode root,int rootsum){
       if(root==null) return false;
       int lsum = sum(root.left);
       int rsum = sum(root.right);
       if(lsum==rsum) return false;
       if(lsum>rsum){
           if(lsum==rsum+root.data+rootsum) return true;
           return isEqual(root.left,rsum+root.data+rootsum);
       }
       else{
           if(rsum==lsum+root.data+rootsum) return true;
           return isEqual(root.right,lsum+root.data+rootsum);
       }
    }
    
    public static int sum(BinaryTreeNode root){
        if(root==null) return 0;
        int lh = sum(root.left);
        int rh = sum(root.right);
        return root.data+lh+rh;
    }

	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		int arr[] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
		BinaryTreeNode root = construct(arr);
		System.out.println(isEqual(root,0));
        sc.close();
	}
}
