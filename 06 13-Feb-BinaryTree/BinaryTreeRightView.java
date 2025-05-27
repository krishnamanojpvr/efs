// Balbir Singh is working with Binary Trees.
// The elements of the tree are given in level-order format.

// Balbir is observing the tree from the right side, meaning he 
// can only see the rightmost nodes (one node per level).

// You are given the root of a binary tree. Your task is to determine 
// the nodes visible from the right side and return them in top-to-bottom order.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// A list of integers representing the node values visible from the right side


// Sample Input-1:
// ---------------
// 1 2 3 5 -1 -1 5

// Sample Output-1:
// ----------------
// [1, 3, 5]



// Sample Input-2:
// ---------------
// 3 2 4 3 2

// Sample Output-2:
// ----------------
// [3, 4, 2]


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


public class BinaryTreeRightView 
{
	public static BinaryTreeNode insert(int[] arr)
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
    
    public static void rightSide(BinaryTreeNode root,int level, ArrayList<Integer> res){
        if(root==null || root.data==-1){
            return;
        }
        if(res.size()==level){
            res.add(root.data);
        }
        rightSide(root.right,level+1,res);
        rightSide(root.left,level+1,res);
    }

	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		int arr[] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
		BinaryTreeNode root = insert(arr);
		ArrayList<Integer> al = new ArrayList<>();
		rightSide(root,0,al);
		System.out.println(al);
        sc.close();
	}
}
