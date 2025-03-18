// Imagine you're the curator of an ancient manuscript archive. Each manuscript is
// assigned a unique significance score, and the archive is arranged so that every 
// manuscript on the left has a lower score and every manuscript on the right has a
// higher score, forming a special ordered display. Now, for an upcoming exhibition,
// scholars have decided that only manuscripts with significance scores between low 
// and high (inclusive) are relevant. Your task is to update the archive by removing
// any manuscripts whose scores fall outside the range [low, high]. Importantly, 
// while you remove some manuscripts, you must preserve the relative order of the 
// remaining ones—if a manuscript was originally displayed as a descendant of another, 
// that relationship should remain intact. It can be proven that there is a unique 
// way to update the archive.

// Display the manuscript of the updated archive. Note that the main manuscript 
// (the root) may change depending on the given score boundaries.

// Input format:
// Line 1: space separated scores to build the manuscript archive
// Line 2: two space seperated integers, low and high.

// Output format:
// space separated scores of the updated archive

// Example 1:
// input=
// 1 0 2
// 1 2
// output=
// 1 2

// Explanation:
// Initial archieve:
//       1
//      / \
//     0   2


// Updated archieve:
//     1
//      \
//       2
// After removing manuscripts scores below 1 (i.e. 0), only the manuscript with 1 
// and its right manuscript 2 remain.

// Example 2:
// input=
// 3 0 4 2 1
// 1 3
// output=
// 3 2 1

// Explanation:
// Initial archieve:
//           3
//          / \
//         0   4
//          \
//           2
//          /
//         1

// Updated archieve:
//       3
//      /
//     2
//    /
//   1


import java.util.*;
class Node{
    int data;
    Node left=null;
    Node right=null;
    Node(int data){
        this.data=data;
    }
}
public class ManuscriptDelete{
    public static Node insertBST(Node root,int val){
        if(root==null){
            return new Node(val);
        }
        else if(val>root.data){
           root.right = insertBST(root.right,val);
        }
        else{
            root.left = insertBST(root.left,val);
        }
        return root;
    }
    public static Node buildTreeBST(int arr[]){
        if(arr.length==0) return null;
        Node root = new Node(arr[0]);
        for(int i=1;i<arr.length;i++){
            root = insertBST(root,arr[i]);
        }
        return root;
    }
    public static Node removeNodes(Node node,int low,int high){
        if(node == null) return null;
        if(node.data<low) return removeNodes(node.right,low,high);
        if(node.data>high) return removeNodes(node.left,low,high);
        node.left = removeNodes(node.left,low,high);
        node.right = removeNodes(node.right,low,high);
        return node;
    }
    public static ArrayList<Integer> level(Node root) {
        ArrayList<Integer> res = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int n = q.size();
            for (int i = 0; i < n; i++) {
                Node node = q.poll();
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
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int arr[] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        int low = sc.nextInt();
        int high = sc.nextInt();
        Node root = buildTreeBST(arr);
        root = removeNodes(root,low,high);
        ArrayList<Integer> res = level(root);
        System.out.println(res);
        sc.close();
        }
}