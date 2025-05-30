/*
Lowest Common Ancestor of a Binary Tree

Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between
two nodes p and q as the lowest node in T that has both p and q as descendants
(where we allow a node to be a descendant of itself).”


Example-1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.


Example-2
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.


Example 3:
Input: root = [1,2], p = 1, q = 2
Output: 1



Input Format:
-------------
Line-1: Single line of space separated integers, person ID's in the family.
Line-2: Two NOdes P1 and P2.

Output Format:
--------------
Return the latest common ascendant of P1 and P2.


Sample Input-1:
---------------
3 5 1 6 2 0 8 -1 -1 7 4
6 4

Sample Output-1:
----------------
5

Sample Input-2:
---------------
11 99 88 77 22 33 66 55 10 20 30 40 50 60 44
66 55

Sample Output-2:
----------------
11
=== testcases ===
case =1
input =3 5 1 6 2 0 8 -1 -1 7 4
6 4
output =5

case =2
input =11 99 88 77 22 33 66 55 10 20 30 40 50 60 44
66 55
output =11

case =3
input =3 5 1 6 2 0 8 -1 -1 7 4
5 8
output =3

case =4
input =3 5 1 6 2 0 8 -1 -1 7 4
1 8
output =1

case =5
input =11 99 88 77 22 33 66 55 10 20 30 40 50 60 44
33 60
output =88

case=6
input =11 99 88 77 22 33 66 55 10 20 30 40 50 60 44
33 50
output =33

case =7
input =3 6 4 7 8 -1 -1 2 9 -1 -1 18 -1 -1 -1 16 -1 
16 9
output =7

case =8
input =3 6 4 7 8 -1 -1 2 9 -1 -1 18 -1 -1 -1 16 -1 5
16 8
output =6

*/

import java.util.*;


public class  Test
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        
        String[] arr= sc.nextLine().split(" ");
        String[] persons = sc.nextLine().split(" ");
        
        List<Integer> v = new ArrayList<>();

        int n=arr.length;


        for (int i = 0; i < n; i++) 
        {
            v.add(Integer.parseInt(arr[i]));
        }
        
        Node root = new Node(v.get(0));

        Node P1 = new Node(Integer.parseInt(persons[0]));

        Node P2 = new Node(Integer.parseInt(persons[1]));

        Queue<Node> q = new LinkedList<>();
        Queue<Node> q2 = new LinkedList<>();
        
        q.add(root);
        
        int j = 1;
        while (j < n && !q.isEmpty())
         {
            Node temp = q.poll();
            if (v.get(j) != -1)
             {
                temp.left = new Node(v.get(j));
                q.add(temp.left);
            }
        
            j++;
            
            if (j < n && v.get(j) != -1)
              {
                temp.right = new Node(v.get(j));
                q.add(temp.right);
            }
            
            j++;
        }

        Node res=new Solution().lowestCommonAscendant(root, P1, P2);
        System.out.println(res.data);
  }
}

class Node 
{
    public int data;
    public Node left;
    public Node right;
    public Node(int value)
 {
        data = value;
        left = null;
        right = null;
    }
}

class Solution {
    Node lowestCommonAscendant(Node root,Node P1, Node P2){
        if (root == null || root.data == P1.data || 
           root.data == P2.data)
	
	 return root;
        Node left = lowestCommonAscendant(root.left, P1, P2);

        Node right = lowestCommonAscendant(root.right, P1, P2);

        return left == null ? right : right == null ? left : root;
    }
}



