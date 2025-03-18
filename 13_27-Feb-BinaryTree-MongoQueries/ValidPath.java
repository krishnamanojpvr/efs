// Imagine you are navigating a maze where each path you take has a section with a 
// code. The maze is structured as a series of interconnected rooms, 
// represented as a tree structure. Each room in the maze has a code (integral value)
// associated with it, and you are trying to check if a given sequence of code 
// matches a valid path from the entrance to an exit. 

// You are provide with the maze structure, where you have to build the maze and then,
// you are provided with a series of space seperated digits, representing a journey 
// starting from the entrance and passing through the rooms along the way. 
// The task is to verify whether the path corresponding to this array of codes 
// exists in the maze.

// Example 1:
// Input:
// 0 1 0 0 1 0 -1 -1 1 0 0         //maze structure
// 0 1 0 1                         //path to verify

// Output: true

// Explanation:
//                0
//              /   \
//             1     0
//            / \    /
//           0   1  0
//            \  / \
//             1 0  0

// The given path 0 → 1 → 0 → 1 is a valid path in the maze. 
// Other valid sequences in the maze include 0 → 1 → 1 → 0 and 0 → 0 → 0.

// Example 2:
// Input:
// 1 2 3
// 1 2 3

// output: false

// Explanation:
// The proposed path 1 → 2 → 3 does not exist in the maze, 
// so it cannot be a valid path.

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

public class ValidPath {
    public static boolean validpath(Node root, int[] path, int index) {
        if (index >= path.length)
            return true;
        if (root == null)
            return false;
        if (root.data != path[index])
            return false;
        return validpath(root.left, path, index + 1) || validpath(root.right, path, index + 1);
    }

    public static Node insert(int[] arr) {
        int n = arr.length;
        Node root = new Node(arr[0]);
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        int ind = 1;
        while (ind < n && !q.isEmpty()) {
            Node temp = q.poll();
            if (arr[ind] != -1) {
                temp.left = new Node(arr[ind]);
                q.add(temp.left);
            }
            ind++;
            if (ind < n && arr[ind] != -1) {
                temp.right = new Node(arr[ind]);
                q.add(temp.right);
            }
            ind++;
        }
        return root;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int maze[] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        int path[] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        Node root = insert(maze);
        System.out.print(validpath(root, path, 0));
        sc.close();
    }
}
