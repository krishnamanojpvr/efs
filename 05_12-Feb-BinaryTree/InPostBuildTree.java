// Given the in-order and post-order traversals of a binary tree, construct 
// the original binary tree. For the given Q number of queries, 
// each query consists of a lower level and an upper level. 
// The output should list the nodes in the order they appear in a level-wise.

// Input Format:
// -------------
// An integer N representing the number nodes.
// A space-separated list of N integers representing the similar to in-order traversal.
// A space-separated list of N integers representing the similar to post-order traversal.
// An integer Q representing the number of queries.
// Q pairs of integers, each representing a query in the form:
// Lower level (L)
// Upper level (U)

// Output Format:
// For each query, print the nodes in order within the given depth range

// Example
// Input:
// 7
// 4 2 5 1 6 3 7
// 4 5 2 6 7 3 1
// 2
// 1 2
// 2 3
// Output:
// [1, 2, 3]
// [2, 3, 4, 5, 6, 7]

// Explanation:
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7
// Query 1 (Levels 1 to 2): 1 2 3
// Query 2 (Levels 2 to 3): 2 3 4 5 6 7

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

public class InPostBuildTree{
    
    public static Node helper(int[] in,int[] post,int inleft, int inright, int postleft, int postright,HashMap<Integer,Integer> inmap){
        if(inright<inleft || postleft>postright){
            return null;
        }
        int data = post[postright];
        Node root = new Node(data);
        int idx = inmap.get(data);
        int subtreelen = idx-inleft;
        root.left = helper(in,post,inleft,idx-1,postleft,postleft+subtreelen-1,inmap);
        root.right = helper(in,post,idx+1,inright,postleft+subtreelen,postright-1,inmap);
        return root;
        
    }
    
    public static Node solve(int in[],int post[],int n){
        HashMap<Integer,Integer> inmap = new HashMap<>();
        for(int i=0;i<n;i++){
            inmap.put(in[i],i);
        }
        return helper(in,post,0,n-1,0,n-1,inmap);
        
        
    }
    
    public static HashMap<Integer,ArrayList<Integer>> levelOrderTraversal(Node root){
        HashMap<Integer,ArrayList<Integer>> levelorder = new HashMap<>();
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        int level = 1;
        while(!q.isEmpty()){
            int n = q.size();
            levelorder.put(level,new ArrayList<Integer>());
            for(int i=0;i<n;i++){
                Node node = q.poll();
                levelorder.get(level).add(node.data);
                if(node.left!=null){
                    q.add(node.left);
                }
                if(node.right!=null){
                    q.add(node.right);
                }
            }
            level++;
        }
        return levelorder;
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int in[] = new int[n];
        int post[] = new int[n];
        for(int i=0;i<n;i++){
            in[i] = sc.nextInt();
        }
        for(int i=0;i<n;i++){
            post[i] = sc.nextInt();
        }
        int q = sc.nextInt();
        int queries[][] = new int[q][2];
        for(int i=0;i<q;i++){
            for(int j=0;j<2;j++){
                queries[i][j] = sc.nextInt();
            }
        }
        Node root = solve(in,post,n);
        HashMap<Integer,ArrayList<Integer>> lo = levelOrderTraversal(root);
        for(int[] query : queries){
            int low = query[0];
            int high = query[1];
            ArrayList<Integer> al = new ArrayList<>();
            for(int i=low;i<=high;i++){
                al.addAll(lo.get(i));
            }
            System.out.println(al);
        }
        sc.close();
        
    }
}