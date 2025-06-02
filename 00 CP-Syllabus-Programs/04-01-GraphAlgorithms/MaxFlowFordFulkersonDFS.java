/*
Max Flow Problem:(FORD FULKERSON ALGORITHM)
    
    Suppose a flow network were each edge has a maximum flow capacity. 
    The max flow problem is all about finding the maximum flow that we can get when
    going from a single-source vertex to a destination vertex. 
    An edge can't exceed it's capacity.

    This information can be useful in computer networks to find the maximum bandwidth
    that an connection can have or even at finding the maximum water capacity of 
    an water supply network!

    This problem can be represented as an Graph again where each Edge has a maximum flow
    capacity or weight and we then try to find the maximum flow using an algorithm.
    I will use the Adjacency Matrix representation that we also used in the 
    Floyd Warshall algorithm, but I will change the initialization of the Edges
    (edge doesn't exist) to zero instead of MAX_VALUE or infinity. 
    In the same way we will use the BFS Algorithm, but change it so that it checks 
    for an path from one specific source vertex to an destination vertex and also 
    stores the path information in an parent array.

The main algorithm is the Ford-Fulkerson Algorithm that does the following:

1.It gets a graph and source and destination vertices as Input
2.It creates an residual graph initialized equal to the input graph and initializes maxflow=0
3.It loops as long as BFS (or DFS) returns a path from source to vertex and

    ->Finds the maximum path flow
    ->Updates the residual graph and reverse edges along the path
    ->Adds the path flow to the maxflow
4. Lastly returns the max flow



Input Format:
------------------
Line-1: An integer V, number of vertices in graph
Next V lines: V space separated integers, graph[][], 
                       the adjacency matrix of the grpah.
Last Line: Two space separated integers, s and t, source and sink.

Output Format:
--------------------
Print an integer, the maximum possible flow of the graph


Sample Input:
------------------
6
0 16 13 0 0 0
0 0 10 12 0 0
0 4 0 0 14 0
0 0 9 0 0 20
0 0 0 7 0 4
0 0 0 0 0 0
0 5

Sample Output:
--------------------
23




case=1
input=
6
0 16 13 0 0 0
0 0 10 12 0 0
0 4 0 0 14 0
0 0 9 0 0 20
0 0 0 7 0 4
0 0 0 0 0 0
0 5

output=
23
*/

import java.util.*;

public class MaxFlowFordFulkersonDFS
 {

    static  int V ; // Number of vertices (you can change this dynamically if needed)

    // Returns true if there is a path from source to sink in residual graph
    // and fills parent[] to store the path
    boolean dfs(int[][] rGraph, int s, int t, int[] parent) 
    {
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        stack.push(s);
        visited[s] = true;
        parent[s] = -1;

        while (!stack.isEmpty()) 
        {
            int u = stack.pop();

            for (int v = 0; v < V; v++) 
            {
                if (!visited[v] && rGraph[u][v] > 0) 
                {
                    stack.push(v);
                    parent[v] = u;
                    visited[v] = true;
                    if (v == t) return true;
                }
            }
        }
        return false;
    }

    // Returns the maximum flow from s to t in the given graph
    int fordFulkerson(int[][] graph, int s, int t) 
    {
        int u, v;

        // Create residual graph
        int[][] rGraph = new int[V][V];
        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                rGraph[u][v] = graph[u][v];

        int[] parent = new int[V];
        int maxFlow = 0;

        // Augment the flow while there is a path from source to sink
        while (dfs(rGraph, s, t, parent)) 
        {
            int pathFlow = Integer.MAX_VALUE;

            // Find minimum residual capacity along the path filled by DFS
            for (v = t; v != s; v = parent[v])
             {
                u = parent[v];
                pathFlow = Math.min(pathFlow, rGraph[u][v]);
            }

            // Update residual capacities
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= pathFlow;
                rGraph[v][u] += pathFlow;
            }

            // Add path flow to overall flow
            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    public static void main(String[] args) throws java.lang.Exception
	{
		Scanner sc=new Scanner(System.in);
		V=sc.nextInt();
		// Let us create a graph shown in the above example
		int graph[][] = new int[V][V];
		for(int i=0;i<V;i++)
		for(int j=0;j<V;j++)
			graph[i][j]=sc.nextInt();
		int s = sc.nextInt();
		int t = sc.nextInt();
		System.out.println(new Test().fordFulkerson(graph, s, t));
        sc.close();
	}
}
