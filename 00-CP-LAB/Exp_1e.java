/*
e) Write a JAVA Program to implement TREAP with its operations 
Given an integer array nums, return the number of reverse pairs in the array. A reverse pair is a 
pair (i, j) where 0 <= i < j < nums.length and nums[i] > 2 * nums[j].
Example 1: 
Input: nums = [1,3,2,3,1] 
Output: 2 
Example 2: 
Input: nums = [2,4,3,5,1] 
Output: 3 
Constraints: 
1 <= nums.length <= 5 * 104 -2^31 <= nums[i] <= 2^31 – 1 
*/

import java.util.*;

class TreapNode {
    int value, priority, size;
    TreapNode left, right;

    TreapNode(int value) {
        this.value = value;
        this.priority = new Random().nextInt();
        this.size = 1;
    }

    void updateSize() {
        this.size = 1 + getSize(left) + getSize(right);
    }

    static int getSize(TreapNode node) {
        return node == null ? 0 : node.size;
    }
}

public class Exp_1e {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int nums[] = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        System.out.println(reversePairs(nums));
        sc.close();
    }

    static int reversePairs(int[] nums) {
        TreapNode root = null;
        int count = 0;

        for (int i = nums.length - 1; i >= 0; i--) {
            count += countLessThan(root, nums[i] / 2.0);
            root = insert(root, nums[i]);
        }
        return count;
    }

    static int countLessThan(TreapNode node, double target) {
        if (node == null)
            return 0;
        if (node.value < target) {
            return TreapNode.getSize(node.left) + 1 + countLessThan(node.right, target);
        } else {
            return countLessThan(node.left, target);
        }
    }

    static TreapNode insert(TreapNode root, int value) {
        if (root == null)
            return new TreapNode(value);
        if (value < root.value) {
            root.left = insert(root.left, value);
            if (root.left.priority > root.priority) {
                root = rotateRight(root);
            }
        } else {
            root.right = insert(root.right, value);
            if (root.right.priority > root.priority) {
                root = rotateLeft(root);
            }
        }
        root.updateSize();
        return root;
    }

    static TreapNode rotateRight(TreapNode root) {
        TreapNode newRoot = root.left;
        root.left = newRoot.right;
        newRoot.right = root;
        root.updateSize();
        newRoot.updateSize();
        return newRoot;
    }

    static TreapNode rotateLeft(TreapNode root) {
        TreapNode newRoot = root.right;
        root.right = newRoot.left;
        newRoot.left = root;
        root.updateSize();
        newRoot.updateSize();
        return newRoot;
    }
}
