// Balbir Singh is working with Binary Trees.
// The elements of the tree is given in the level order format.
// Balbir has a task to split the tree into two parts by removing only one edge
// in the tree, such that the product of sums of both the splitted-trees should be maximum.

// You will be given the root of the binary tree.
// Your task is to help the Balbir Singh to split the binary tree as specified.
// print the product value, as the product may be large, print the (product % 1000000007)

// NOTE: 
// Please do consider the node with data as '-1' as null in the given trees.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print an integer value.

// Sample Input-1:
// ---------------
// 1 2 4 3 5 6

// Sample Output-1:
// ----------------
// 110

// Explanation:
// ------------
// if you split the tree by removing edge between 1 and 4, 
// then the sums of two trees are 11 and 10. So, the max product is 110.

// Sample Input-2:
// ---------------
// 3 2 4 3 2 -1 6

// Sample Output-2:
// ----------------
// 100

import java.util.*;

class BinaryTreeNode {
    public int data = -1;
    public BinaryTreeNode left, right;

    public BinaryTreeNode(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

public class MaxProduct {
    static HashSet<Integer> set = new HashSet<>();

    public static BinaryTreeNode construct(int[] arr) {
        int n = arr.length;
        BinaryTreeNode root = new BinaryTreeNode(arr[0]);
        Queue<BinaryTreeNode> q = new LinkedList<>();
        q.offer(root);
        int ind = 1;
        while (ind < n && !q.isEmpty()) {
            BinaryTreeNode temp = q.poll();
            if (arr[ind] != -1) {
                temp.left = new BinaryTreeNode(arr[ind]);
                q.add(temp.left);
            }
            ind++;
            if (ind < n && arr[ind] != -1) {
                temp.right = new BinaryTreeNode(arr[ind]);
                q.add(temp.right);
            }
            ind++;
        }
        return root;
    }

    public static int maxprod(BinaryTreeNode root, int rootsum) {
        int max = Integer.MIN_VALUE;
        if (root == null)
            return 0;
        int lsum = sum(root.left);
        int rsum = sum(root.right);
        max = Math.max(max, Math.max(lsum * (rsum + root.data + rootsum), (lsum + root.data + rootsum) * rsum));
        max = Math.max(Math.max(maxprod(root.left, root.data + rootsum + rsum),
                maxprod(root.right, root.data + rootsum + lsum)), max);
        return max;

    }

    public static int maxprodOptimized(BinaryTreeNode root) {
        int treesum = sum(root);
        int max = Integer.MIN_VALUE;
        for (int x : set) {
            max = Math.max((treesum - x) * (x), max);
        }
        return max % 1000000007;

    }

    public static int sum(BinaryTreeNode root) {
        if (root == null)
            return 0;
        int lh = sum(root.left);
        int rh = sum(root.right);
        set.add(lh);
        set.add(rh);
        return root.data + lh + rh;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int arr[] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        BinaryTreeNode root = construct(arr);
        // System.out.println(maxprod(root,0));
        System.out.println(maxprodOptimized(root));
        sc.close();
    }
}
