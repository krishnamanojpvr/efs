
/* A Java program to find articulation points in an undirected graph

Problem Statement: Given an undirected connected graph with V vertices and adjacency list adj. You are required to find all the vertices removing which (and edges through it) disconnect the graph into 2 or more components.

Note: Indexing is zero-based i.e nodes numbering from (0 to V-1). There might be loops present in the graph.

Pre-requisite: Bridges in Graph problem & DFS algorithm.


STEPS:
In order to find all the articulation points of a graph, we will implement some logic over the DFS algorithm. 
This is more of an algorithm-based approach. So, let’s discuss the algorithm in detail. Before that,
 we will discuss the two important concepts of the algorithm i.e. time of insertion and lowest time of insertion.

 1.Time of insertion: Dring the DFS call, the time when a node is visited, is called its time of insertion. For example, 
    if in the above graph, we start DFS from node 0 it will visit node 1 first then node 2, node 3, and so on. So, 
    the time of insertion for node 0 will be 1, node 1 will be 2, node 2 will be 3 and it will continue like this. 
    We will use a time array to store the insertion time for each node.This definition remains the same as it was during the bridge problem.
2.Lowest time of insertion: In this case, the current node refers to all its adjacent nodes except the parent and
  the visited nodes and takes the minimum lowest time of insertion into account. To store this entity for each node, 
  we will use another ‘low’ array.
   The difference in finding the lowest time of insertion in this problem is that in the bridgealgorithm, we only excluded the parent node but in this algorithm, we are excluding the visited nodes along with the parent node.


---->The logical modification of the DFS algorithm is discussed below:

1.To find out the bridges in the bridge problem, we checked inside the DFS, if there exists any alternative path from the adjacent node to the current node.
But here we cannot do so as in this case, we are trying to remove the current node along with all the edges linked to it. For that reason, here we will check if there exists any path from the adjacent node to the previous node of the current node. In addition to that, we must ensure that the current node we are trying to remove must not be the starting node. 

     The check conditions for this case will change like the following:
   
     if(low[it] > tin[node])  converts to if(low[it] >= tin[node] && parent  != -1) 
      
       For the starting node, we will apply different logic.



       
case=1

enter number of vertices
5
enter number of  edges
5
enter edges
1
0
0
2
2
1
0
3
3
4
Articulation points in first graph
0 3


*/
import java.util.*;

class Graph {

	static int time;

	static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
		adj.get(u).add(v);
		adj.get(v).add(u);
	}

	static void APUtil(ArrayList<ArrayList<Integer>> adj, int disc[], int low[], boolean visited[], boolean isAP[],
			int u, int parent) {
		// Count of children in DFS Tree
		int children = 0;

		// Mark the current node as visited
		visited[u] = true;

		// Initialize discovery time and low value
		disc[u] = low[u] = ++time;

		// Go through all vertices adjacent to this
		for (Integer v : adj.get(u)) {
			if(v==parent) continue;
			// If v is not visited yet, then make it a child of u
			// in DFS tree and recur for it
			if (!visited[v]) {
				children++;
				APUtil(adj,disc,low,visited,isAP,u,v);

				// Check if the subtree rooted with v has
				// a connection to one of the ancestors of u
				low[u] = Math.min(low[u], low[v]);

				// If u is not root and low value of one of
				// its child is more than discovery value of u.
				if (parent != -1 && low[v] >= disc[u])
					isAP[u] = true;
			}

			// Update low value of u for parent function calls.
			else if (v != parent)
				low[u] = Math.min(low[u], disc[v]);
		}

		// If u is root of DFS tree and has two or more children.
		if (parent == -1 && children > 1)
			isAP[u] = true;
	}

	public static void AP(ArrayList<ArrayList<Integer>> adj, int V) {
		boolean[] vis = new boolean[V];
		int[] disc = new int[V];
		int[] low = new int[V];
		boolean[] isAP = new boolean[V];
		int parent = -1;

		// Adding this loop so that thecode works even if we are given disconnected
		// graph
		for (int u = 0; u < V; u++)
			if (vis[u] == false)
				APUtil(adj, disc, low, vis, isAP, u, parent);

		for (int u = 0; u < V; u++)
			if (isAP[u] == true)
				System.out.print(u + " ");
		System.out.println();
	}

	public static void main(String[] args) {

		// Creating first example graph
		Scanner sc = new Scanner(System.in);
		System.out.println("enter number of vertices ");
		int V = sc.nextInt();
		System.out.println("enter number of  edges");
		int e = sc.nextInt();
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(V);

		for (int i = 0; i < V; i++) {
			adj.add(new ArrayList<Integer>());
		}
		System.out.println("enter edges");
		for (int i = 0; i < e; i++) {
			int end1 = sc.nextInt();
			int end2 = sc.nextInt();
			addEdge(adj, end1, end2);
		}

		System.out.println("Articulation points in first graph");
		AP(adj, V);
		sc.close();

	}
}
