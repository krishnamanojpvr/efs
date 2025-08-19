// There are N cities in a state.
// The cities are connected with two types of roadways:
// 1) concrete roadway 2) asphalt roadway.
// The roadways may be parallel.

// You are given the lists of concrete roadways and asphalt roadways
// between the cities. All roadways are unidirectional.
// Concrete_Roadway[i,j] indiactes: a concrete road from city-i to city-j. Similarly,
// Asphalt[i,j] indiactes: an asphalt road from city-i to city-j. Similarly,

// Your task is to find and return the list of lengths of the shortest paths from 
// city-0 to city-P, where P start from 0 to N-1. And the path should contains 
// alternative roadways like as follows: concrete - asphalt - concrete -asphalt --etc
// or vice versa. If there is no such shortest path exist print -1.

// Input Format:
// -------------
// Line-1: 3 space separated integers N, C & A, Number of cities, routes between the cities.
// 		number of concrete roads and number of asphalt roads
// Next C lines: Two space separated integers, concrete road between city-i to city-j.		
// Next A lines: Two space separated integers, asphalt road between city-i to city-j.		

// Output Format:
// --------------
// Print the list of lengths, the shortest paths.


// Sample Input-1:
// ---------------
// 4 2 1
// 0 1
// 2 3
// 1 2

// Sample Output-1:
// ----------------
// 0 1 2 3

// Sample Input-2:
// ---------------
// 4 2 1
// 1 0
// 2 3
// 1 2

// Sample Output-2:
// ----------------
// 0 -1 -1 -1


// Sample Input-3:
// ---------------
// 4 3 2
// 1 0
// 1 2
// 2 3
// 0 1
// 1 2

// Sample Output-3:
// ----------------
// 0 1 2 -1


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class ShortestCityPaths {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int c = sc.nextInt();
        int a = sc.nextInt();
        
        Map<Integer, List<Integer>> concrete = new HashMap<>();
        Map<Integer, List<Integer>> asphalt = new HashMap<>();
        
        for (int i = 0; i < c; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            concrete.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        }
        for (int i = 0; i < a; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            asphalt.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        }
        
        int[] result = bfs(n, concrete, asphalt);
        for (int x : result) System.out.print(x + " ");
        sc.close();
    }
    
    static int[] bfs(int n, Map<Integer, List<Integer>> concrete, Map<Integer, List<Integer>> asphalt) {
        int[][] dist = new int[n][2]; // [city][lastEdgeType]
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        
        Queue<int[]> q = new LinkedList<>();
        
        dist[0][0] = 0; 
        dist[0][1] = 0;
        q.offer(new int[]{0, 0}); // last was concrete
        q.offer(new int[]{0, 1}); // last was asphalt
        
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int node = curr[0], type = curr[1];
            int nextType = 1 - type; // alternate road
            
            List<Integer> neighbors = (nextType == 0) ? 
                concrete.getOrDefault(node, new ArrayList<>()) : 
                asphalt.getOrDefault(node, new ArrayList<>());
            
            for (int nei : neighbors) {
                if (dist[nei][nextType] > dist[node][type] + 1) {
                    dist[nei][nextType] = dist[node][type] + 1;
                    q.offer(new int[]{nei, nextType});
                }
            }
        }
        
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int d = Math.min(dist[i][0], dist[i][1]);
            ans[i] = (d == Integer.MAX_VALUE) ? -1 : d;
        }
        return ans;
    }
}