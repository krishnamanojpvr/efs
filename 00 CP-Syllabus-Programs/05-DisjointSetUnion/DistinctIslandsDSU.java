/*
 Number of Distinct Islands
Problem Statement:
Given an n x m binary matrix where 1s represent land and 0s represent water, an island is a group of 1s connected 4-directionally (horizontally or vertically). Two islands are considered the same if one can be translated (shifted without rotation or reflection) to match the other. The task is to find the number of distinct islands.

Key Points:
Islands are connected only horizontally or vertically (4-directional).

Two islands are identical if they have the same shape when translated (shifted) to the same position.

Rotation or reflection does NOT make two islands identical.

Examples:
Example 1:
Input:

[
 [1,1,0,0,0],
 [1,1,0,0,0],
 [0,0,0,1,1],
 [0,0,0,1,1]
]
Output: 1

Explanation:

There are two islands (top-left and bottom-right).

Both islands have the same 2x2 square shape.

Since they can be translated to match each other, they are considered the same.

Hence, the number of distinct islands = 1.

Example 2:
Input:

[
 [1,1,0,1,1],
 [1,0,0,0,0],
 [0,0,0,0,1],
 [1,1,0,1,1]
]
Output: 3

Explanation:

There are four islands:

Top-left island (L-shape).

Top-right island (2x1 vertical bar).

Bottom-left island (2x1 vertical bar).

Bottom-right island (L-shape).

Top-right and bottom-left islands are identical (same vertical bar shape).

Top-left and bottom-right islands are different (mirrored L-shapes are not considered the same).

Hence, the number of distinct islands = 3.
 */

/*
 Approach to Solve the Problem:
Traverse the Grid (DFS/BFS):

For each 1 found, explore the entire island using DFS/BFS.

Record Island Shape:

While exploring, record the relative coordinates of each 1 with respect to the starting cell (to account for translation).

Store the shape as a string (e.g., "0,0;0,1;1,0" for an L-shaped island).

Use a HashSet for Distinct Shapes:

Add each island's shape string to a HashSet (automatically handles duplicates).

Return the Size of the HashSet:

The number of unique strings = number of distinct islands.
 */

import java.util.*;

public class DistinctIslandsUF {
    private int[] size;
    private int[] p;
    private int N, M;

    public int find(int i) {
        while (p[i] >= 0)
            i = p[i];
        return i;
    }

    public void union(int i, int j) {
        int ri = find(i);
        int rj = find(j);
        if (ri == rj)
            return;
        if (size[ri] < size[rj]) {
            size[rj] += size[ri];
            p[ri] = rj;
        } else {
            size[ri] += size[rj];
            p[rj] = ri;
        }
    }

    private boolean inside(int x, int y) {
        return (x >= 0 && y >= 0 && x < N && y < M);
    }

    public int numIslands(int[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;
        N = grid.length;
        M = grid[0].length;
        size = new int[N * M];
        p = new int[N * M];
        List<int[]> ones = new ArrayList<>();

        for (int i = 0; i < N * M; i++) {
            p[i] = -1;
            size[i] = 1;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] != 0) {
                    int[] pos = new int[2];
                    pos[0] = i;
                    pos[1] = j;
                    ones.add(pos);
                    int tmp = i * M + j;
                    if (inside(i - 1, j) && grid[i - 1][j] != 0)
                        union(tmp, tmp - M);
                    if (inside(i, j - 1) && grid[i][j - 1] != 0)
                        union(tmp, tmp - 1);
                    if (inside(i + 1, j) && grid[i + 1][j] != 0)
                        union(tmp, tmp + M);
                    if (inside(i, j + 1) && grid[i][j + 1] != 0)
                        union(tmp, tmp + 1);
                }
            }
        }

        HashMap<Integer, Queue<int[]>> map = new HashMap<>();
        for (int[] pos : ones) {
            int x = pos[0], y = pos[1], root = find(x * M + y);
            Queue<int[]> queue = map.getOrDefault(root, new LinkedList<>());
            queue.add(pos);
            map.put(root, queue);
        }

        HashSet<String> strs = new HashSet<>();
        for (int k : map.keySet()) {
            Queue<int[]> queue = map.get(k);
            int[] dd = queue.peek();
            int dx = dd[0], dy = dd[1];
            StringBuilder sb = new StringBuilder();
            while (!queue.isEmpty()) {
                int[] cur = queue.remove();
                sb.append(cur[0] - dx);
                sb.append(",");
                sb.append(cur[1] - dy);
                sb.append(";");
            }
            strs.add(sb.toString());
        }

        return strs.size();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] grid = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
        System.out.println(new DistinctIslandsUF().numIslands(grid));
        sc.close();
    }
}