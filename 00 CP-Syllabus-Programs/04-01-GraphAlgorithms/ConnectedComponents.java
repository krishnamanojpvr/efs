/*Java program to print connected components in an undirected graph Using DFS

Definition:

A connected component or simply component of an undirected graph is a subgraph in which each pair of nodes is connected with each other via a path.

Let’s try to simplify it further, though. A set of nodes forms a connected component in an undirected graph if any node from the set of nodes can reach any other node by traversing edges. The main point here is reachability.

In connected components, all the nodes are always reachable from each other.

enter number of vertices
5
enter number of  edges
3
enter edges
1
0
2
1
3
4
Following are connected components
0 1 2
3 4
Numbr of Components:2
*/

import java.util.*;

class ConnectedComponents 
{
	// A user define class to represent a graph.
	// A graph is an array of adjacency lists.
	// Size of array will be V (number of vertices in graph)
	int V;
	ArrayList<ArrayList<Integer> > adjListArray;

	// constructor
	ConnectedComponents(int V)
	{
		this.V = V;
		// define the size of array as number of vertices
		adjListArray = new ArrayList<>();

		// Create a new list for each vertex such that adjacent nodes can be stored

		for (int i = 0; i < V; i++) 
		{
			adjListArray.add(i, new ArrayList<>());
		}
	}

	// Adds an edge to an undirected graph
	void addEdge(int src, int dest)
	{		// Add an edge from src to dest.
		adjListArray.get(src).add(dest);

		// Since graph is undirected, add an edge from dest
		// to src also
		adjListArray.get(dest).add(src);
	}

	void connectedComponents()
	{
		// Mark all the vertices as not visited
		boolean[] visited = new boolean[V];
		int count=0;
		for (int v = 0; v < V; ++v)
		 {
			if (!visited[v]) 
			{
				// print all reachable vertices from v
				DFSUtil(v, visited);
				count++;
				System.out.println();
			}
		}
		System.out.println("Numbr of Components:"+count);
	}
	void DFSUtil(int v, boolean[] visited)
	{
		// Mark the current node as visited and print it
		visited[v] = true;
		System.out.print(v + " ");
		// Recursion for all the vertices adjacent to this vertex
		for (int x : adjListArray.get(v))
		 {
			if (!visited[x])
				DFSUtil(x, visited);
		}
	}
	

	public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter number of vertices ");
        int v=sc.nextInt();
     	System.out.println("enter number of  edges");
        int e=sc.nextInt();
        
       ConnectedComponents g = new ConnectedComponents(v);
        System.out.println("enter edges");
        for(int i=0;i<e;i++)
        {
            int end1=sc.nextInt();
            int end2=sc.nextInt();
            g.addEdge(end1,end2);
        }
 
       
		System.out.println("Following are connected components");
		g.connectedComponents();
	}
}



