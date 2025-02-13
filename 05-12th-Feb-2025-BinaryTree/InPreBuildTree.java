// Construct the binary tree from the given In-order and Pre-order. 
// Find Nodes Between Two Levels in Spiral Order.
// The spiral order is as follows:
// -> Odd levels → Left to Right.
// -> Even levels → Right to Left.

// Input Format:
// --------------
// An integer N representing the number of nodes.
// A space-separated list of N integers representing the in-order traversal.
// A space-separated list of N integers representing the pre-order traversal.
// Two integers:
// Lower Level (L)
// Upper Level (U)

// Output Format:
// Print all nodes within the specified levels, but in spiral order.

// Example:
// Input:
// 7
// 4 2 5 1 6 3 7
// 1 2 4 5 3 6 7
// 2 3

// Output:
// 3 2 4 5 6 7

// Explanation:
// Binary tree structure:
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7

// Levels 2 to 3 in Regular Order:
// Level 2 → 2 3
// Level 3 → 4 5 6 7

// Spiral Order:
// Level 2 (Even) → 3 2 (Right to Left)
// Level 3 (Odd) → 4 5 6 7 (Left to Right)


import java.util.*;

class Node{
    int data;
    Node left;
    Node right;
    
    Node(int data){
        this.data=data;
        this.left=null;
        this.right=null;
    }
}

public class InPreBuildTree{
    
    public static Node helper(int[] in,int[] pre,int inleft, int inright, int preleft, int preright,HashMap<Integer,Integer> inmap){
        if(inright<inleft || preleft>preright){
            return null;
        }
        int data = pre[preleft];
        Node root = new Node(data);
        int idx = inmap.get(data);
        int subtreelen = idx-inleft;
        root.left = helper(in,pre,inleft,idx-1,preleft+1,preleft+subtreelen,inmap);
        root.right = helper(in,pre,idx+1,inright,preleft+subtreelen+1,preright,inmap);
        return root;
        
    }
    
    public static Node solve(int in[],int pre[],int n){
        HashMap<Integer,Integer> inmap = new HashMap<>();
        for(int i=0;i<n;i++){
            inmap.put(in[i],i);
        }
        return helper(in,pre,0,n-1,0,n-1,inmap);
        
        
    }
    
    public static ArrayList<Integer> levelOrderTraversal(Node root,int lb,int ub){
        ArrayList<Integer> res = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        int level = 1;
        while(!q.isEmpty()){
            int n = q.size();
            ArrayList<Integer> temp = new ArrayList<>();
            for(int i=0;i<n;i++){
                Node node = q.poll();
                temp.add(node.data);
                if(node.left!=null){
                    q.add(node.left);
                }
                if(node.right!=null){
                    q.add(node.right);
                }
            }
            if(level>=lb && level<=ub){
                if(level%2==0){
                    Collections.reverse(temp);
                    res.addAll(temp);
                }
                else{
                    res.addAll(temp);
                }
            }
            level++;
        }
        return res;
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int in[] = new int[n];
        int pre[] = new int[n];
        for(int i=0;i<n;i++){
            in[i] = sc.nextInt();
        }
        for(int i=0;i<n;i++){
            pre[i] = sc.nextInt();
        }
        int lb = sc.nextInt();
        int ub = sc.nextInt();
        
        Node root = solve(in,pre,n);
        ArrayList<Integer> res = levelOrderTraversal(root,lb,ub);
        System.out.println(res);
        sc.close();
    }
}