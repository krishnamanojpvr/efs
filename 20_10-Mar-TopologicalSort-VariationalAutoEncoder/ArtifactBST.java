// Imagine you're the chief curator at a renowned museum that houses a rare collection 
// of ancient artifacts. These artifacts are arranged in a special display that 
// follows a strict rule: any artifact positioned to the left of another has a lower 
// value, and any artifact on the right has a higher value. 

// Your task is to transform this display into what is known as a "Greater Artifact 
// Display." In this new arrangement, each artifact’s new value will be its original 
// value plus the sum of the values of all artifacts that are more valuable than it.

// Example 1:
// ----------
// input=
// 4 2 6 1 3 5 7
// output=
// 22 27 13 28 25 18 7

// Explanation:
// Input structure 
//        4
//       / \
//      2   6
//     / \ / \
//    1  3 5  7

// Output structure:
//         22
//       /   \
//      27   13
//     / \   / \
//    28 25 18  7

// Reverse updates:
// - Artifact 7: 7
// - Artifact 6: 6 + 7 = 13
// - Artifact 5: 5 + 13 = 18
// - Artifact 4: 4 + 18 = 22
// - Artifact 3: 3 + 22 = 25
// - Artifact 2: 2 + 25 = 27
// - Artifact 1: 1 + 27 = 28


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

public class ArtifactBST 
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
    
    public static void dfs(BinaryTreeNode root,int[] sum){
        if(root==null) return;
        dfs(root.right,sum);
        sum[0]+=root.data;
        root.data=sum[0];
        dfs(root.left,sum);
    }
    
    public static ArrayList<Integer> level(BinaryTreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        Queue<BinaryTreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int n = q.size();
            for (int i = 0; i < n; i++) {
                BinaryTreeNode node = q.poll();
                res.add(node.data);
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
        }
        return res;
    }
    

	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		int arr[] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
		BinaryTreeNode root = construct(arr);
		dfs(root,new int[]{0});
		ArrayList<Integer> res = level(root);
		System.out.println(res);
        sc.close();
	}
}
