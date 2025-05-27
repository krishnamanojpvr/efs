// Imagine you are a librarian organizing books on vertical shelves in a grand 
// library. The books are currently scattered across a tree-like structure, where 
// each book (node) has a position determined by its shelf number (column) and row 
// number (level).

// Your task is to arrange the books on shelves so that:
// 1. Books are placed column by column from left to right.
// 2. Within the same column, books are arranged from top to bottom (i.e., by row).
// 3. If multiple books belong to the same shelf and row, they should be arranged 
// from left to right, just as they appear in the original scattered arrangement.

// Sample Input:
// -------------
// 3 9 20 -1 -1 15 7

// Sample Output:
// --------------
// [[9],[3,15],[20],[7]]

// Explanation:
// ------------
//          3
//        /   \
//       9     20
//           /    \
//          15     7

// Shelf 1: [9]
// Shelf 2: [3, 15]
// Shelf 3: [20]
// Shelf 4: [7]


// Sample Input-2:
// ---------------
// 3 9 8 4 0 1 7

// Sample Output-2:
// ----------------
// [[4],[9],[3,0,1],[8],[7]]

// Explanation:
// ------------

//           3
//        /     \
//       9       8
//     /   \   /   \
//    4     0 1     7

// Shelf 1: [4]
// Shelf 2: [9]
// Shelf 3: [3, 0, 1]
// Shelf 4: [8]
// Shelf 5: [7]

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

class Pair{
    BinaryTreeNode node;
    int level;
    Pair(BinaryTreeNode node,int level){
        this.node = node;
        this.level=level;
    }
}


public class VerticalTraversal 
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
    
    
    public static TreeMap<Integer,ArrayList<Integer>> vertical(BinaryTreeNode root){
        TreeMap<Integer,ArrayList<Integer>> tm = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root,0));
        while(!q.isEmpty()){
            Pair p = q.poll();
            tm.putIfAbsent(p.level,new ArrayList<Integer>());
            tm.get(p.level).add(p.node.data);
            if(p.node.left!=null) q.add(new Pair(p.node.left,p.level-1));
            if(p.node.right!=null) q.add(new Pair(p.node.right,p.level+1));
        }
        return tm;
    }
    
    

	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		int arr[] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
		BinaryTreeNode root = construct(arr);
		TreeMap<Integer,ArrayList<Integer>> tm = vertical(root);
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		for(ArrayList<Integer> i : tm.values()){
		    res.add(i);
		}
		System.out.println(res);
        sc.close();
	}
}
