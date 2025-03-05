// There are N computers in a network, all the computers are connected as tree 
// structure. And one new connection is added in the Network. The computers in 
// the network are identified with their IDs, the IDs are numbered between 1 to N.

// The connections in the network is given as coonection[i] = [comp-A, comp-B], 
// there is a connection between comp-A and comp-B.

// Your task is to remove a connection in the network and print it, so that 
// all the computers are connected as tree structure. If there are multiple 
// options to remove, remove the connection that occurs last in the input.

// Input Format:
// -------------
// Line-1: Two space separated integers N, number of computers.
// Next N lines: Two space separated integers, comp-A & comp-B.

// Output Format:
// --------------
// Print the connection which is removed.

// Sample Input-1:
// ---------------
// 6
// 1 2
// 3 4
// 3 6
// 4 5
// 5 6
// 2 3

// Sample Output-1:
// ---------------
// 5 6

// Sample Input-2:
// ---------------
// 4
// 1 2
// 2 3
// 3 4
// 2 4

// Sample Output-2:
// ---------------
// 2 4

import java.util.*;

public class RedundantEdges {
    public static int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    public static void union(int[] parent, int rank[], int a, int b, int[] prev) {
        int x = find(parent, a);
        int y = find(parent, b);
        if (x == y) {
            if (prev[0] != -1) {
                parent[prev[1]] = prev[0];
                parent[prev[2]] = prev[0];
            }
            prev[0] = x;
            prev[1] = a;
            prev[2] = b;
        }
        if (rank[x] > rank[y])
            parent[y] = x;
        else if (rank[y] > rank[x])
            parent[x] = y;
        else {
            parent[x] = y;
            rank[y]++;
        }
    }

    public static int[] redundant(int arr[][], int m) {
        int parent[] = new int[m + 1];
        for (int i = 0; i <= m; i++) {
            parent[i] = i;
        }
        int rank[] = new int[m + 1];
        int prev[] = new int[3];
        for (int i = 0; i < m; i++) {
            union(parent, rank, arr[i][0], arr[i][1], prev);
        }
        return prev;

    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int arr[][] = new int[m][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 2; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        int res[] = redundant(arr, m);
        System.out.println(res[1] + " " + res[2]);
        sc.close();
    }
}
