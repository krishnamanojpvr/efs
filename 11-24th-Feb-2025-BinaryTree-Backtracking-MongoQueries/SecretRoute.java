// Imagine you are designing a network of secret corridors in an ancient castle. 
// Each chamber in the castle leads to at most two other chambers through 
// hidden passageways, forming a branching layout. 
// The castle’s "longest secret route" is defined as the maximum number of corridors 
// you must traverse to get from one chamber to another (without repeating the corridor). 
// This route may or may not pass through the main entry chamber.

// Your task is to compute the length of longest secret route between 
// two chambers which is represented by the number of corridors between them.

// Example 1
// input=
// 1 2 3 4 5 
// output=
// 3

// Structure:
//        1
//       / \
//      2   3
//     / \
//    4   5

// Explanation:
// The longest secret route from chamber 4 to chamber 3 
// (alternatively, from chamber 5 to chamber 3) along the route:
// 4 → 2 → 1 → 3
// This path has 3 corridors (4–2, 2–1, 1–3), so the length is 3.

// Example 2:
// input=
// 1 -1 2 3 4
// output=
// 2

// Structure:
//    1
//     \
//      2
//     / \
//    3   4

// Explanation:
// The longest secret route from chamber 3 to chamber 4 
// (alternatively, from chamber 1 to chamber 4) along the route:
// 3 → 2 → 4
// This path has 2 corridors (3–2, 2–4), so the length is 2.



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

public class SecretRoute{
    static int res = 0;
    public static int helper(Node root){
        if(root==null){
            return 0;
        }
        int lh = helper(root.left);
        int rh = helper(root.right);
        res = Math.max(lh+rh,res);
        return Math.max(lh,rh)+1;
    }
    public static int distance(Node root){
        if(root==null) return 0;
        int ld = helper(root.left);
        int rd = helper(root.right);
        return Math.max(res,ld+rd);
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
		System.out.print(distance(root));
        sc.close();
	}
}
