// Budget Padmanabham planned to visit the tourist places. There are N tourist 
// places in the city. The tourist places are numbered from 1 to N.

// You are given the routes[] to travel between the tourist places in the city.
// where routes[i]=[place-1, place-2, price], A route is a bi-directional route.
// You can travel from place-1 to place-2 or place-2 to place-1 with the given price.
 
// Your task is to help Budget Padmanabham to find the cheapest route plan, to 
// visit all the tourist places in the city. If you are not able to find such plan, 
// print -1.
 
// Input Format:
// -------------
// Line-1: Two space separated integers N and R,number of places and routes.
// Next R lines: Three space separated integers, start, end and price.
  
// Output Format:
// --------------
// Print an integer, minimum cost to visit all the tourist places.
 
 
// Sample Input-1:
// ---------------
// 4 5
// 1 2 3
// 1 3 5
// 2 3 4
// 3 4 1
// 2 4 5
 
// Sample Output-1:
// ----------------
// 8
 
// Explanation:
// ------------
// The cheapest route plan is as follows:
// 1-2, 2-3, 3-4 and cost is 3 + 4 + 1 = 8
 
 
// Sample Input-2:
// ---------------
// 4 3
// 1 2 3
// 1 3 5
// 2 3 4
 
// Sample Output-2:
// ----------------
// -1


import java.util.*;

public class MinCostKruskals {
    public static int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    public static void union(int[] parent, int rank[], int a, int b, int c, int[] sum,Set<Integer> set,int count[]) {
        int x = find(parent, a);
        int y = find(parent, b);
        if (x == y) {
           return;
        }
        sum[0]+=c;
        count[0]++;
        if (rank[x] > rank[y]){
            parent[y] = x;
            set.remove(y);
        }
        else if (rank[y] > rank[x]){
            parent[x] = y;
            set.remove(x);
        }
        else {
            parent[x] = y;
            rank[y]++;
            set.remove(x);
        }
    }

    public static int kruskal(ArrayList<int[]> al, int n,int m) {
        Set<Integer> set = new HashSet<>();
        int count[] = new int[]{m};
        int parent[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            set.add(i);
        }
        int rank[] = new int[n + 1];
        int sum[] = new int[]{0};
        for (int arr[] : al) {
            union(parent, rank, arr[0], arr[1], arr[2],sum,set,count);
            if(count[0]==n-1){
                return sum[0];
            }
        }
        if(set.size()!=1) return -1;
        return sum[0];

    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        ArrayList<int[]> al = new ArrayList<>();
        int n = sc.nextInt();
        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            int arr[] = new int[3];
            for (int j = 0; j < 3; j++) {
                arr[j] = sc.nextInt();
            }
            al.add(arr);
        }
        Collections.sort(al, (a, b) -> (a[2] - b[2]));
        System.out.println(kruskal(al, n, m));
        sc.close();
    }
}
