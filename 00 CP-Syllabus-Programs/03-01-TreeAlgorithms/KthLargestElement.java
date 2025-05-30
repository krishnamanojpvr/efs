/*
Given an integer array nums and an integer k, return the kth largest element in the array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.

You must solve it in O(n) time complexity.

Example 1:
Input: nums = [3,2,1,5,6,4], k = 2
Output: 5

Example 2:
Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4

Note: You are suppose to print the K'th largest height in the sorted order of heights[].
      Not the K'th distinct height.

=== testcases ===

case=1
input=6 3
2 4 3 1 6 5
output=4

case=2
input=6 2
3 2 1 5 6 4
output=5

case=3
input=9 4
3 2 3 1 2 4 5 5 6
output=4

case=4
input=10 4
9 5 4 6 9 1 2 8 9 8
output=8

case=5
input=10 3
9 5 2 1 3 6 4 7 8 10
output=8

*/

import java.util.*;
class TreapNode
{
    int data;
    int priority;
    TreapNode left;
    TreapNode right;
    TreapNode(int data)
    {
        this.data = data;
        this.priority = new Random().nextInt(1000);
        this.left = this.right = null;
    }
}
class KthLargest
{
    static int k;
    public static TreapNode rotateLeft(TreapNode root)
    {
        TreapNode R = root.right;
        TreapNode X = root.right.left;
        R.left = root;
        root.right = X;
        return R;
    }
    public static TreapNode rotateRight(TreapNode root)
    {
        TreapNode L = root.left;
        TreapNode Y = root.left.right;
        L.right = root;
        root.left = Y;
        return L;
    }
    public static TreapNode insertNode(TreapNode root, int data){
        if (root == null) {
            return new TreapNode(data);
        }
        if (data < root.data)
        {
            root.left = insertNode(root.left, data);
            if (root.left != null && root.left.priority > root.priority) {
                root = rotateRight(root);
            }
        }
        else {
            root.right = insertNode(root.right, data);
            if (root.right != null && root.right.priority > root.priority) {
                root = rotateLeft(root);
            }
        }
 
        return root;
    }
    static void inorder(TreapNode root)
    {
        if (root == null)
            return;
        inorder(root.left);
        k--;
        if(k==0){
           System.out.print(root.data);
           return;
        }
        inorder(root.right);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int p = sc.nextInt();
        k=n-p+1;
        int arr[] = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        TreapNode root = null;
        for(int a:arr){
            root = insertNode(root,a);
        }
        inorder(root);
    }
}