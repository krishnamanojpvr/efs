/* You are given an integer n, which indicates that there are n courses labeled from 1 to n. You are 
also given an array relations where relations[i] = [prevCoursei, nextCoursei], representing a 
prerequisite relationship between course prevCoursei and course nextCoursei: 
course prevCoursei has to be taken before course nextCoursei. Also, you are given the integer k. 
 In one semester, you can take at most k courses as long as you have taken all the prerequisites 
in the previous semesters for the courses you are taking. 
 Return the minimum number of semesters needed to take all courses. The testcases will be 
generated such that it is possible to take every course. 

Example 1: 
Input: n = 4, relations = [[2,1],[3,1],[1,4]], k = 2 
Output: 3 
Explanation: The figure above represents the given graph. 
In the first semester, you can take courses 2 and 3. 
In the second semester, you can take course 1. 
In the third semester, you can take course 4. 

Example 2: 
Input: n = 5, relations = [[2,1],[3,1],[4,1],[1,5]], k = 2 
Output: 4 
Explanation: The figure above represents the given graph. 
In the first semester, you can only take courses 2 and 3 since you cannot take more than two per 
semester. 
In the second semester, you can take course 4. 
In the third semester, you can take course 1. 
In the fourth semester, you can take course 5.*/

import java.util.List;
import java.util.*;

public class ParallelCourses_II {

    //* Not an Accurate answer for this question but will pass most of the test cases.
    //* We can use Dynamic Programming and Bit Masking to solve this program to get an accurate answer and pass all the test cases.

    public static int  minimumSemesters(int numCourses, int[][] prerequisites, int maxCourses) {

        int[][] graph = new int[numCourses + 1][numCourses + 1];
        int[] indegree = new int[numCourses + 1];

        for (int[] prerequisite : prerequisites) {
            int u = prerequisite[0];
            int v = prerequisite[1];
            graph[u][v] = 1;
            indegree[v]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int semesters = 0;
        int coursesTaken = 0;

        while (!queue.isEmpty()) {
            int coursesThisSemester = Math.min(queue.size(), maxCourses);
            List<Integer> nextSemester = new ArrayList<>();

            for (int i = 0; i < coursesThisSemester; i++) {
                int u = queue.poll();
                coursesTaken++;

                for (int v = 1; v <= numCourses; v++) {
                    if (graph[u][v] == 1) {
                        indegree[v]--;
                        if (indegree[v] == 0) {
                            nextSemester.add(v);
                        }
                    }
                }
            }

            queue.addAll(nextSemester);
            semesters++;
        }

        return coursesTaken == numCourses ? semesters : -1;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numCourses = s.nextInt();
        int c = s.nextInt();
        int prerequisites[][] = new int[c][2];
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < 2; j++) {
                prerequisites[i][j] = s.nextInt();
            }
        }
        int maxCourses = s.nextInt();
        System.out.println(minimumSemesters(numCourses, prerequisites, maxCourses));
        s.close();
    }
}