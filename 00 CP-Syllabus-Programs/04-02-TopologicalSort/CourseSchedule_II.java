/*Course Schedule II

There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. 
You are given an array prerequisites where prerequisites[i] = [ai, bi] 
indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. 
If there are many valid answers, return any of them. 
If it is impossible to finish all courses, return an empty array.

Example 1:
Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].

Example 2:
Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].

Example 3:
Input: numCourses = 1, prerequisites = []
Output: [0]
 


 case=1
 input=
 2
 1
 1 0
 output=
 [0,1]
 
 case=2
 input=
 4
 4
 1 0
 2 0
 3 1
 3 2
 output=
[0,2,1,3]

case=3
input=
1
0
output=
[]
*/
import java.util.*;

class CourseSchedule_II 

 {
    public int[] findOrder(int numCourses, int[][] prerequisites)
     {
        // first task is to create graph..
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0 ; i < numCourses; i++)
        {
            graph.add(new ArrayList<>());
        }

        for(int i = 0 ; i < prerequisites.length; i++)
        {
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }

        // create an indegree array for each node...
        int[] indegree = new int[numCourses];
        
        for(int i = 0; i < numCourses ; i++)
        {
            for(int node : graph.get(i))
            {
                indegree[node]++;
            }
        }

        // now adding node to q, which have indegree 0...
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < indegree.length; i++)
        {
            if(indegree[i] == 0){
                q.add(i);
            }
        }


        // topological sorted array..
        int[] ts = new int[numCourses];
        int i = 0;
        
        while(!q.isEmpty())
        {
            int node = q.remove();
            ts[i++] = node;

            for(int nbr : graph.get(node))
            {
                indegree[nbr]--;
                if(indegree[nbr] == 0)
                {
                    q.add(nbr);
                }
            }

        }
        if(i == 0 || i < numCourses) return new int[]{};
        return ts;
    }
    public static void main(String[] args)
     {
        Scanner sc = new Scanner(System.in);
        CourseSchedule_II  cs = new CourseSchedule_II ();

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

        System.out.println( cs.findOrder(numCourses, prerequisites));
        sc.close();
    }
}