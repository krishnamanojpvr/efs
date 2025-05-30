/*CP_U_IV_TOPOLOGICAL SORT:- Parallel Courses

There are N courses, labelled from 1 to N.

We are given relations[i] = [X, Y], representing a prerequisite relationship between course X and course Y: course X has to be studied before course Y.

In one semester you can study any number of courses as long as you have studied all the prerequisites for the course you are studying.

Return the minimum number of semesters needed to study all courses.  
If there is no way to study all the courses, return -1.

EXAMPLE-1:

Input: N = 3, relations = [[1,3],[2,3]]
Output: 2
Explanation: 
In the first semester, courses 1 and 2 are studied. In the second semester, course 3 is studied.

Example-2:
Input: N = 3, relations = [[1,2],[2,3],[3,1]]
Output: -1
Explanation: 
No course can be studied because they depend on each other.


case =1
input =3
3
1 3
2 3
3 1
output =-1

case =2
input =3
2
1 3
2 3
output =2

case =3
input =10
10
1 3
2 3
3 4
7 6
4 6
5 6
6 8
6 9
8 10
9 10
output =6

case =4
input =10
10
1 5
2 5
3 5
4 5
5 8
6 8
7 8
8 10
7 9
9 10
output =4

case =5
input =
15
16
1 5
2 5
3 6
4 6
5 7
6 7
7 9
6 8
8 9
9 10
9 11
10 12
11 13
12 14
13 14
14 15
output =8

*/
import java.util.*;

public class Parallelcourses 
{

    public int minimumSemesters(int N, int[][] relations) 
    {
        // Step 1: Create adjacency list and indegree array
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= N; i++)
         {
            adj.add(new ArrayList<>());
        }

        int[] indegree = new int[N + 1];
        for (int[] rel : relations) 
        {
            int u = rel[0];
            int v = rel[1];
            adj.get(u).add(v);
            indegree[v]++;
        }

        // Step 2: Initialize queue with nodes having indegree 0
        Queue<Integer> queue = new LinkedList<>();
       
        for (int i = 1; i <= N; i++) 
        {
            if (indegree[i] == 0) 
            {
                queue.offer(i);
            }
        }

        int semester = 0;
        int completedCourses = 0;

        // Step 3: Topological sort with level/semester tracking
        while (!queue.isEmpty()) 
        {
            int size = queue.size(); // Number of courses you can take in current semester
            semester++;

            for (int i = 0; i < size; i++) 
            {
                int course = queue.poll();
                completedCourses++;

                for (int neighbor : adj.get(course))
                {
                    indegree[neighbor]--;
                    if (indegree[neighbor] == 0)
                     {
                        queue.offer(neighbor);
                    }
                }
            }
        }

        // Step 4: Check if all courses are completed
        return completedCourses == N ? semester : -1;
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        Parallelcourses cs = new Parallelcourses();

        // Read number of courses
       
        int N = sc.nextInt();

        // Read number of relations
       
        int M = sc.nextInt();

        // Create an array to store relations
        int[][] relations = new int[M][2];

        // Read the relations
      
        for (int i = 0; i < M; i++)
         {
            relations[i][0] = sc.nextInt();  // course X
            relations[i][1] = sc.nextInt();  // course Y
        }

        // Call the method to compute minimum semesters
        System.out.println( cs.minimumSemesters(N, relations));
      

        sc.close();
    }
}
