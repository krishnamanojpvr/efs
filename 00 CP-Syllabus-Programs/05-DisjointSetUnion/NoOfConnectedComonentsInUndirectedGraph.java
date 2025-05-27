/*
Given an undirected graph G with vertices numbered in the range [0, N] and an array  
Edges[][] consisting of M edges, the task is to find the total number of connected 
components in the graph using Disjoint Set Union algorithm. 
Examples: 
Input: N = 5, Edges[][] = {{1, 0}, {2, 3}, {3, 4}} 
Output: 2 
Explanation: There are only 2 connected components as shown below: 
Input: N = 8, Edges[][] = {{1, 0}, {0, 2}, {3, 5}, {3, 4}, {6, 7}} 
Output: 3 
Explanation: There are only 3 connected components as shown below: 
 */
    
import java.util.*;
public class NoOfConnectedComonentsInUndirectedGraph{
    public static int find(int[] parent,int x){
        if(parent[x]!=x){
            parent[x] = find(parent,parent[x]);
        }
        return parent[x];
    }
    public static void union(int[] parent,int rank[],int a,int b){
        int x = find(parent,a);
        int y = find(parent,b);
        if(x==y) return;
        if(rank[x]>rank[y]) parent[y] = x;
        else if(rank[y]>rank[x]) parent[x] = y;
        else{
            parent[x] = y;
            rank[y]++;
        }
    }
    public static int regions(int arr[][],int n,int m){
        int parent[] = new int[n];
        for(int i=0;i<n;i++){
            parent[i] = i;
        }
        int rank[] = new int[n];
        for(int i=0;i<m;i++){
            union(parent,rank,arr[i][0],arr[i][1]);
        }
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<n;i++){
            set.add(find(parent,i));
        }
        return set.size();
        
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int arr[][] = new int[m][2];
        for(int i=0;i<m;i++){
            for(int j=0;j<2;j++){
                arr[i][j] = sc.nextInt();
            }
        }
        System.out.println(regions(arr,n,m));
        sc.close();
    }
}
