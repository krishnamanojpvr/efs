import java.util.*;

public class MaxFlowFordFulkerson_DFS
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
	}
}
