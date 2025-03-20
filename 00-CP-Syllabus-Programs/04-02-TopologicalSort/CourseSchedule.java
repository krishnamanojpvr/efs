/*There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. 
You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must
 take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid answers,
return any of them. If it is impossible to finish all courses, return an empty array.

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So the correct course order is [0,1].
Example 2:

Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. 
To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
Example 3:

Input: numCourses = 1, prerequisites = []
Output: [0]*/

import java.util.*;

public class CourseSchedule {
    public static ArrayList<Integer> topoBFS(ArrayList<int[]> al, int n, int m) {
        Map<Integer, ArrayList<Integer>> adj = new HashMap<>();
        Map<Integer, Integer> indeg = new HashMap<>();
        for (int[] i : al) {
            adj.putIfAbsent(i[1], new ArrayList<Integer>());
            adj.get(i[1]).add(i[0]);
            indeg.put(i[0], indeg.getOrDefault(i[0], 0) + 1);
        }
        Queue<Integer> q = new LinkedList<>();
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!indeg.containsKey(i))
                q.offer(i);
        }
        while (!q.isEmpty()) {
            int independent = q.poll();
            res.add(independent);
            if (adj.containsKey(independent)) {
                for (int neigh : adj.get(independent)) {
                    indeg.put(neigh, indeg.get(neigh) - 1);
                    if (indeg.get(neigh) == 0) {
                        q.offer(neigh);
                        indeg.remove(neigh);
                    }
                }
            }
        }
        return res;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        ArrayList<int[]> al = new ArrayList<>();
        int n = sc.nextInt();
        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            int arr[] = new int[2];
            for (int j = 0; j < 2; j++) {
                arr[j] = sc.nextInt();
            }
            al.add(arr);
        }
        System.out.println(topoBFS(al, n, m));
        sc.close();
    }
}