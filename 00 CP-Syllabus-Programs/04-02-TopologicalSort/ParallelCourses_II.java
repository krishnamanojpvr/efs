/*paraller Courses-II

1.	You are given an integer n, which indicates that there are n courses labeled from 1 to n. You are also given an array relations where relations[i] = [prevCoursei, nextCoursei], representing a prerequisite relationship between course prevCoursei and course nextCoursei: course prevCoursei has to be taken before course nextCoursei. Also, you are given the integer k.
2.	In one semester, you can take at most k courses as long as you have taken all the prerequisites in the previous semesters for the courses you are taking.
3.	Return the minimum number of semesters needed to take all courses. The testcases will be generated such that it is possible to take every course.

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
In the first semester, you can only take courses 2 and 3 since you cannot take more than two per semester.
In the second semester, you can take course 4.
In the third semester, you can take course 1.


case=1
input=
Enter number of courses (n): 4
Enter number of prerequisite relations: 3
Enter prerequisite pairs (a b) :
2 1
3 1
1 4
Enter maximum number of courses per semester (k): 2
output=
Minimum number of semesters: 3

case=2
input=
Enter number of courses (n): 5
Enter number of prerequisite relations: 4
Enter prerequisite pairs (a b) :
2 1
3 1
4 1
1 5
Enter maximum number of courses per semester (k): 2
output=
Minimum number of semesters: 4
*/
import java.util.*;

public class Parallelcourses_II
 {

    public int minNumberOfSemesters(int n, int[][] relations, int k)
     {
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[n + 1];

        for (int i = 0; i <= n; i++) 
        graph.add(new ArrayList<>());

        for (int[] rel : relations) 
        {
            int u = rel[0], v = rel[1];
            graph.get(u).add(v);
            indegree[v]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++)
         {
            if (indegree[i] == 0) 
            queue.offer(i);
        }

        int semesters = 0;

        while (!queue.isEmpty()) 
        {
            int size = queue.size();
            int canTake = Math.min(size, k);
            List<Integer> currentSemester = new ArrayList<>();

            for (int i = 0; i < canTake; i++) 
            {
                currentSemester.add(queue.poll());
            }

            for (int course : currentSemester) 
            {
                for (int neighbor : graph.get(course))
                 {
                    indegree[neighbor]--;
                    if (indegree[neighbor] == 0) 
                    {
                        queue.offer(neighbor);
                    }
                }
            }

            semesters++;
        }

        return semesters;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Parallelcourses_II courses = new Parallelcourses_II();

        // Read number of courses
        System.out.print("Enter number of courses (n): ");
        int n = sc.nextInt();

        // Read number of relations
        System.out.print("Enter number of prerequisite relations: ");
        int m = sc.nextInt();

        int[][] relations = new int[m][2];
        System.out.println("Enter prerequisite pairs (a b) :");

        for (int i = 0; i < m; i++) {
            relations[i][0] = sc.nextInt();
            relations[i][1] = sc.nextInt();
        }

        // Read k (max courses per semester)
        System.out.print("Enter maximum number of courses per semester (k): ");
        int k = sc.nextInt();

        int result = courses.minNumberOfSemesters(n, relations, k);
        System.out.println("Minimum number of semesters: " + result);
    }
}
