// A software development company is designing a smart home automation 
// system that uses sensor networks to monitor and control different devices 
// in a house. The sensors are organized in a hierarchical structure, where each 
// sensor node has a unique ID and can have up to two child nodes (left and right).

// The company wants to analyze the left-most sensors in the system to determine
// which ones are critical for detecting environmental changes. The hierarchy of 
// the sensors is provided as a level-order input, where missing sensors are 
// represented as -1.

// Your task is to build the sensor network as a binary tree and then determine 
// the left-most sensor IDs at each level.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// A list of integers representing the left-most sensor IDs at each level


// Sample Input-1:
// ---------------
// 1 2 3 4 -1 -1 5

// Sample Output-1:
// ----------------
// [1, 2, 4]



// Sample Input-2:
// ---------------
// 3 2 4 1 5

// Sample Output-2:
// ----------------
// [3, 2, 1]

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


public class BinaryTreeLeftView 
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
    
    public static void leftSide(BinaryTreeNode root,int level, ArrayList<Integer> res){
        if(root==null || root.data==-1){
            return;
        }
        if(res.size()==level){
            res.add(root.data);
        }
        leftSide(root.left,level+1,res);
        leftSide(root.right,level+1,res);
    }

	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		int arr[] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
		BinaryTreeNode root = insert(arr);
		ArrayList<Integer> al = new ArrayList<>();
		leftSide(root,0,al);
		System.out.println(al);
        sc.close();
	}
}
