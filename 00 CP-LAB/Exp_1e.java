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
  long key;
  int priority, size;
  TreapNode left, right;

  TreapNode(long key) {
    this.key = key;
    this.priority = new Random().nextInt();
    this.size = 1;
  }
}

class Treap {
  TreapNode root;

  int size(TreapNode node) {
    return node == null ? 0 : node.size;
  }

  void updateSize(TreapNode node) {
    if (node != null) {
      node.size = 1 + size(node.left) + size(node.right);
    }
  }

  TreapNode rotateRight(TreapNode y) {
    TreapNode x = y.left;
    TreapNode T2 = x.right;
    x.right = y;
    y.left = T2;
    updateSize(y);
    updateSize(x);
    return x;
  }

  TreapNode rotateLeft(TreapNode x) {
    TreapNode y = x.right;
    TreapNode T2 = y.left;
    y.left = x;
    x.right = T2;
    updateSize(x);
    updateSize(y);
    return y;
  }

  TreapNode insert(TreapNode node, long key) {
    if (node == null)
      return new TreapNode(key);
    if (key <= node.key) {
      node.left = insert(node.left, key);
      if (node.left.priority < node.priority) {
        node = rotateRight(node);
      }
    } else {
      node.right = insert(node.right, key);
      if (node.right.priority < node.priority) {
        node = rotateLeft(node);
      }
    }
    updateSize(node);
    return node;
  }

  void insert(long key) {
    root = insert(root, key);
  }

  int countLessThan(TreapNode node, long val) {
    if (node == null)
      return 0;
    if (val <= node.key) {
      return countLessThan(node.left, val);
    } else {
      return 1 + countLessThan(node.right, val) + size(node.left);
    }
  }

  int countLessThan(long val) {
    return countLessThan(root, val);
  }
}

public class Exp_1e {
  public static int reversePairs(int[] nums) {
    Treap t = new Treap();
    int cnt = 0;
    for (int i = nums.length - 1; i >= 0; i--) {
      cnt += t.countLessThan((long) nums[i]);
      t.insert(2L * nums[i]);
    }
    return cnt;
  }

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
}