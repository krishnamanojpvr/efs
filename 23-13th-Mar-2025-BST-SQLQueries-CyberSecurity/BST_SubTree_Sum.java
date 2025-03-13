// Imagine you are designing a grand castle where each room holds a specific amount 
// of treasure. The castle is built in a binary layout, meaning every room may lead 
// to two adjacent wings—a left wing and a right wing. 

// An "organized section" of the castle follows this rule: for any given room, 
// every room in its left wing contains a treasure value that is strictly less 
// than the current room’s value, and every room in its right wing contains a 
// value that is strictly greater. Additionally, each wing must itself be organized
// according to the same rule.

// Your challenge is to determine the maximum total treasure (i.e., the sum of 
// treasure values) that can be gathered from any such organized section of the castle.

// Example 1:
// input=
// 1 4 3 2 4 2 5 -1 -1 -1 -1 -1 -1 4 6
// output=
// 20

// Castle:
//           1
//         /   \
//        4     3
//       / \   / \
//      2   4 2   5
//               / \
//              4   6

// Explanation: The best organized section starts at the room with a treasure value 
// of 3, yielding a total treasure of 20.

// Example 2:
// input=
// 4 3 -1 1 2
// output=
// 2

// Castle:
//     4
//    /
//   3
//  / \
// 1   2

// Explanation: The optimal organized section is just the single room with a 
// treasure value of 2.

// Example 3:
// input=
// -4 -2 -5
// output=
// 0

// Castle:
//    -4
//   /  \
// -2   -5
 
// Explanation: Since all rooms contain negative treasure values, no beneficial 
// organized section exists, so the maximum total treasure is 0.

// Constraints:

// - The number of rooms in the castle ranges from 1 to 40,000.
// - Each room’s treasure value is an integer between -40,000 and 40,000.

import java.util.*;
class Node{
    int data;
    Node left=null;
    Node right=null;
    Node(int data){
        this.data=data;
    }
}
public class BST_SubTree_Sum{
   public static Node construct(int[] arr)
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
    public static int dfs(Node root,int[] sum){
        if(root==null) return 0;
        int l = dfs(root.left,sum);
        int r = dfs(root.right,sum);
        if(l>-1 && r>-1){
            if(l!=0 && root.data<=root.left.data){
                return -1;
            }
            if(r!=0 && root.data>=root.right.data){
                return -1;
            }
        }
        else{
            return -1;
        }
        int max = l+r+root.data;
        sum[0] = Math.max(sum[0],max);
        return max;
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int arr[] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        Node root = construct(arr);
        int[] sum = new int[]{0};
        dfs(root,sum);
        System.out.println(sum[0]);
        sc.close();
        }
}