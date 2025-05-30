/*  findung bridges in a given undirected graph  

Definition:
An edge in an undirected connected graph is a bridge if removing it disconnects the graph. For a disconnected undirected graph, 
definition is similar, a bridge is an edge removing which increases number of disconnected components. 
or
Bridge:
Any edge in a component of a graph is called a bridge when the component is divided into 2 or more components if we remove that particular edge.

Approach:

In order to find all the bridges of a graph, we will implement some logic over the DFS algorithm. 
This is more of an algorithm-based approach. 
So, let’s discuss the algorithm in detail. Before that, we will discuss two important concepts of the algorithm i.e. 
time of insertion and lowest time of insertion.

 ->Time of insertion: Dring the DFS call, the time when a node is visited, is called its time of insertion. 
   For example, if in the above graph, we start DFS from node 1 it will visit node 1 first then node 2, node 3, node 4, and so on. So, the time of insertion for node 1 will be 1, node 2 will be 2, node 3 will be 3 and it will continue like this. To store the time of insertion for each node, we will use a time array.
 ->Lowest time of insertion: In this case, the current node refers to all its adjacent nodes except the parent and takes the minimum lowest time of insertion into account. To store this entity for each node, we will use another ‘low’ array.
The logical modification of the DFS algorithm is discussed below:

After the DFS for any adjacent node gets completed, we will just check if the edge, whose starting point is the current node and ending point is that adjacent node, is a bridge. For that, we will just check if any other path from the current node to the adjacent node exists if we remove that particular edge. If any other alternative path exists, this edge is not a bridge. Otherwise, it can be considered a valid bridge. 

Approach: 

The algorithm steps are as follows:

1.First, we need to create the adjacency list for the given graph from the edge information(If not already given). And we will declare a variable timer(either globally or we can carry it while calling DFS), that will keep track of the time of insertion for each node.

2.Then we will start DFS from node 0(assuming the graph contains a single component otherwise, we will call DFS for every component) with parent -1.
   2.1.Inside DFS, we will first mark the node visited and then store the time of insertion and the lowest time of insertion properly. The timer may be initialized to 0 or 1.
   2.2.Now, it’s time to visit the adjacent nodes. 
      2.2.1.If the adjacent node is the parent itself, we will just continue to the next node.
      2.2.2.If the adjacent node is not visited, we will call DFS for the adjacent node with the current node as the parent.
            After the DFS gets completed, we will compare the lowest time of insertion of the current node and the adjacent node and take the minimum one.
            Now, we will check if the lowest time of insertion of the adjacent node is greater than the time of insertion of the current node.
            If it is, then we will store the adjacent node and the current node in our answer array as they are representing the bridge.
     2.2.3.If the adjacent node is already visited, we will just compare the lowest time of insertion of the current node and the adjacent node and take the minimum one.

3.Finally, our answer array will store all the bridges.
Note: We are not considering the parent’s insertion time during calculating the lowest insertion time as we want to check if any other path from the node to the parent exists excluding the edge we intend to remove.


case=1
Bridges in first graph
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
Bridges in graph
3 4
0 3



case=2

enter number of vertices                                                                                                
4                                                                                                                       
enter number of  edges                                                                                                  
3                                                                                                                       
enter edges                                                                                                             
0                                                                                                                       
1                                                                                                                       
1                                                                                                                       
2                                                                                                                       
2                                                                                                                       
3                                                                                                                       
Bridges in graph                                                                                                        
2 3                                                                                                                     
1 2                                                                                                                     
0 1                                                                                                                     



case=3

enter number of vertices
7
enter number of  edges
8
enter edges
0
1
1
2
2
0
1
3
1
4
1
6
3
5
4
5
Bridges in graph
1 6
*/

import java.io.*;
import java.util.*;
import java.util.LinkedList;

// This class represents a undirected graph using adjacency list // representation
class Test
{
    private int V;   // No. of vertices

    // Array  of lists for Adjacency List Representation
    private LinkedList<Integer> adj[];
    int time = 0;
    static final int NIL = -1;

    // Constructor
    @SuppressWarnings("unchecked")
    Test(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }

    // Function to add an edge into the graph
    void addEdge(int v, int w)
    {
        adj[v].add(w);  // Add w to v's list.
        adj[w].add(v);    //Add v to w's list
    }

    // A recursive function that finds and prints bridges     // using DFS traversal
    // u --> The vertex to be visited next
    // visited[] --> keeps track of visited vertices
    // disc[] --> Stores discovery times of visited vertices
    // parent[] --> Stores parent vertices in DFS tree
    
    void bridgeUtil(int u, boolean visited[], int disc[],
                    int low[], int parent[])
    {

        // Mark the current node as visited
        visited[u] = true;

        // Initialize discovery time and low value
        disc[u] = low[u] = ++time;

        // Go through all vertices adjacent to this
        Iterator<Integer> i = adj[u].iterator();
        while (i.hasNext())
        {
            int v = i.next();  // v is current adjacent of u

            // If v is not visited yet, then make it a child
            // of u in DFS tree and recur for it.
            // If v is not visited yet, then recur for it
            if (!visited[v])
            {
                parent[v] = u;
                bridgeUtil(v, visited, disc, low, parent);

                // Check if the subtree rooted with v has a
                // connection to one of the ancestors of u
                low[u]  = Math.min(low[u], low[v]);

                // If the lowest vertex reachable from subtree
                // under v is below u in DFS tree, then u-v is
                // a bridge
                if (low[v] > disc[u])
                    System.out.println(u+" "+v);
            }

            // Update low value of u for parent function calls.
            else if (v != parent[u])
                low[u]  = Math.min(low[u], disc[v]);
        }
    }


    // DFS based function to find all bridges. It uses recursive
    // function bridgeUtil()
    void bridge()
    {
        // Mark all the vertices as not visited
        boolean visited[] = new boolean[V];
        int disc[] = new int[V];
        int low[] = new int[V];
        int parent[] = new int[V];


        // Initialize parent and visited, and ap(articulation point)
        // arrays
        for (int i = 0; i < V; i++)
        {
            parent[i] = NIL;
            visited[i] = false;
        }

        // Call the recursive helper function to find Bridges  in DFS tree rooted with vertex 'i'
        for (int i = 0; i < V; i++)
            if (visited[i] == false)
                bridgeUtil(i, visited, disc, low, parent);
    }

    public static void main(String args[])
    {
        // Create graphs given in above diagrams
        
        Scanner sc=new Scanner(System.in);
        System.out.println("enter number of vertices ");
        int v=sc.nextInt();
     	System.out.println("enter number of  edges");
        int e=sc.nextInt();
        
       Test g = new Test(v);
        System.out.println("enter edges");
        for(int i=0;i<e;i++)
        {
            int end1=sc.nextInt();
            int end2=sc.nextInt();
            g.addEdge(end1,end2);
        }
          System.out.println("Bridges in graph");     
        g.bridge();
       
    }
}