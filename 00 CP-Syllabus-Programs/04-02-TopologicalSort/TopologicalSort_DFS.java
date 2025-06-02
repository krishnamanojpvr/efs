
/*TOPOLOGIOCAL SORT USING DFS
 
input=
enter number of vertices
6
enter number of  edges
9
enter edges
5
0
5
2
2
0
2
3
3
0
3
1
1
0
4
0
4
1
output=
topological order is
5 4 2 3 1 0
 */
import java.util.*;

class TopologicalSort {
	// Topo sort only exists in DAGs i.e. Direct Acyclic Graph
	void dfs(List<Integer>[] adj, List<Integer> vis, int node, int n, Stack<Integer> stck) {
		vis.set(node, 1);
		for (int it : adj[node]) {
			if (vis.get(it) == 0) {
				dfs(adj, vis, it, n, stck);
			}
		}
		stck.push(node);
	}

	// During the traversal u must be visited before v
	Stack<Integer> topo_sort(List<Integer>[] adj, int n) {
		List<Integer> vis = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			vis.add(0);
		}
		// using stack ADT
		Stack<Integer> stck = new Stack<>();
		for (int i = 0; i < n; i++) {
			if (vis.get(i) == 0) {
				dfs(adj, vis, i, n, stck);
			}
		}
		return stck;

	}
}

public class TopologicalSort_DFS {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter number of vertices ");
		int n = sc.nextInt();

		System.out.println("enter number of  edges");
		int e = sc.nextInt();

		List<Integer>[] adj = new ArrayList[n];

		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<>();
		}

		System.out.println("enter edges");
		for (int i = 0; i < e; i++) {
			int end1 = sc.nextInt();
			int end2 = sc.nextInt();
			addEdge(adj, end1, end2);
		}

		TopologicalSort ts = new TopologicalSort();
		System.out.println("topological order is");
		Stack<Integer> ans = ts.topo_sort(adj, n);
		while (!ans.isEmpty()) {
			int node = ans.pop();
			System.out.print(node + " ");
		}
	}

	private static void addEdge(List<Integer>[] adj, int u, int v) {
		adj[u].add(v);
	}
}
