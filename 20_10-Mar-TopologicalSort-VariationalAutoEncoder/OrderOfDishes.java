// public class Program1 {
    
// }
// Imagine you're the master chef in a renowned kitchen, tasked with preparing a 
// spectacular dinner consisting of numDishes unique dishes, labeled from 
// 0 to numDishes - 1. However, the recipes have dependencies—certain dishes can 
// only be prepared after completing others. You’re given a list called dependecies, 
// where each element dependencies[i] = [Xi, Yi] means that you must finish 
// preparing dish Yi before starting dish Xi.

// For instance, the pair [2, 1] implies that to create dish 2, 
// dish 1 must be prepared first.

// Return the ordering of dishes that a chef should take to finish all dishes.
// 	- the result set should follow the given order of conditions.
// 	- If it is impossible to complete all dishes, return an empty set.


// Input Format:
// -------------
// Line-1: An integer numDishes, number of Dishes.
// Line-2: An integer m, number of dependencies.
// Next m lines: Two space separated integers, Xi and Yi.

// Output Format:
// --------------
// Return a list of integers, the ordering of dishes that a chef should finish.

// Example 1:
// ------------
// Input=
// 4
// 3
// 1 2
// 3 0
// 0 1
// Output=
// [2, 1, 0, 3]


// Explanation: There are 4 dishes. Since dish 1 requires dish 2, dish 3 requires 
// dish 0 and dish 0 requires dish 1, you can prepare dishes in the order 2 1 0 3.


// Example 2:
// ----------
// Input=
// 2
// 2
// 1 0
// 0 1
// Output=
// []

// Explanation: There are 2 dishes, but dish 1 depends on dish 0 and dish 0 depends 
// on dish 1. This circular dependency makes it impossible to prepare all dishes.

// Constraints:

// - 1 <= numDishes <= 2000  
// - 0 <= m <= 5000  
// - dependencies[i].length == 2  
// - 0 <= Xi, Yi < numDishes  
// - All the dependency pairs are unique.

import java.util.*;

public class OrderOfDishes {

    //* BFS 
    public static ArrayList<Integer> topoBFS(ArrayList<int[]> al,int n,int m){
        Map<Integer,ArrayList<Integer>> adj = new HashMap<>();
        Map<Integer,Integer> indeg = new HashMap<>();
        for(int[] i : al){
            adj.putIfAbsent(i[1],new ArrayList<Integer>());
            adj.get(i[1]).add(i[0]);
            indeg.put(i[0],indeg.getOrDefault(i[0],0)+1);
        }
        Queue<Integer> q = new LinkedList<>();
        ArrayList<Integer> res = new ArrayList<>();
        for(int i=0;i<n;i++){
            if(!indeg.containsKey(i)) q.offer(i);
        }
        while(!q.isEmpty()){
            int independent = q.poll();
            res.add(independent);
            if(adj.containsKey(independent)){
                for(int neigh : adj.get(independent)){
                    indeg.put(neigh,indeg.get(neigh)-1);
                    if(indeg.get(neigh)==0){
                        q.offer(neigh);
                        indeg.remove(neigh);
                    }
                }
            }
        }
        return res;
    }

    //* DFS

    public static ArrayList<Integer> topoDFS(ArrayList<int[]> al,int n,int m){
        return new ArrayList<>();
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        ArrayList<int[]> al = new ArrayList<>();
        int n = sc.nextInt();
        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            int arr[] = new int[2];
            for (int j = 0; j < 2; j++) {
                arr[j] = sc.nextInt();
            }
            al.add(arr);
        }
        System.out.println(topoBFS(al, n, m));
        sc.close();
    }
}
