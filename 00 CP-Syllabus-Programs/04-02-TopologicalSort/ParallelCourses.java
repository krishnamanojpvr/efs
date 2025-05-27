/*You are given an integer n, which indicates that there are n courses labeled from 1 to n. You are 
also given an array relations where relations[i] = [prevCoursei, nextCoursei], representing a 
prerequisite relationship between course prevCoursei and course nextCoursei: course 
prevCoursei has to be taken before course nextCoursei. 
In one semester, you can take any number of courses as long as you have taken all the 
prerequisites in the previous semester for the courses you are taking. 
Return the minimum number of semesters needed to take all courses. If there is no way to take 
all the courses, return -1.

Example 1: 
Input: n = 3, relations = [[1,3],[2,3]] 
Output: 2 
Explanation: The figure above represents the given graph. 
In the first semester, you can take courses 1 and 2. 
In the second semester, you can take course 3. 

Example 2: 
Input: n = 3, relations = [[1,2],[2,3],[3,1]] 
Output: -1 
Explanation: No course can be studied because they are prerequisites of each other.
*/

import java.util.*;

public class ParallelCourses {

    public static int topoBFS(ArrayList<int[]> al, int n, int m) {
        Map<Integer, ArrayList<Integer>> adj = new HashMap<>();
        Map<Integer, Integer> indeg = new HashMap<>();
        for (int[] i : al) {
            adj.putIfAbsent(i[0], new ArrayList<Integer>());
            adj.get(i[0]).add(i[1]);
            indeg.put(i[1], indeg.getOrDefault(i[1], 0) + 1);
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (!indeg.containsKey(i))
                q.offer(i);
        }
        int semesters = 0;
        while (!q.isEmpty()) {
            semesters++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int courses = q.poll();
                n--;
                if (adj.containsKey(courses)) {
                    for (int neigh : adj.get(courses)) {
                        indeg.put(neigh, indeg.get(neigh) - 1);
                        if (indeg.get(neigh) == 0) {
                            q.offer(neigh);
                            indeg.remove(neigh);
                        }
                    }
                }
            }
        }
        return n == 0 ? semesters : -1;
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
