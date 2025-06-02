/*Course Schedule_I

There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.

 
Example 1:
Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.

Example 2:
Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 
case=1
input=
Enter number of courses: 2
Enter number of prerequisite pairs: 1
Enter prerequisites:
1 0
output=
true


case=2
input=
Enter number of courses: 2
Enter number of prerequisite pairs: 2
Enter prerequisites:
1 0
0 1
output=
false
*/
import java.util.*;

public class CourseSchedule_I
 {

    public boolean canFinish(int numCourses, int[][] prerequisites) 
    {
        // Step 1: Build graph and indegree array
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++)
            graph.add(new ArrayList<>());

        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prereq = pre[1];
            graph.get(prereq).add(course); // prereq -> course
            indegree[course]++;
        }

        // Step 2: Initialize queue with courses having 0 prerequisites
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0)
                queue.offer(i);
        }

        int finishedCourses = 0;

        // Step 3: Process courses
        while (!queue.isEmpty()) {
            int current = queue.poll();
            finishedCourses++;

            for (int neighbor : graph.get(current)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0)
                    queue.offer(neighbor);
            }
        }

        // Step 4: If we finished all courses, return true
        return finishedCourses == numCourses;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CourseSchedule_I cs = new CourseSchedule_I();

        System.out.print("Enter number of courses: ");
        int numCourses = sc.nextInt();

        System.out.print("Enter number of prerequisite pairs: ");
        int m = sc.nextInt();

        int[][] prerequisites = new int[m][2];
        System.out.println("Enter prerequisites");
        for (int i = 0; i < m; i++) {
            prerequisites[i][0] = sc.nextInt();
            prerequisites[i][1] = sc.nextInt();
        }

        boolean canFinish = cs.canFinish(numCourses, prerequisites);
        System.out.println(canFinish);
        sc.close();
    }
}
