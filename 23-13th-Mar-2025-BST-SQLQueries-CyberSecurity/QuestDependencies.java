// Imagine you're playing an adventure video game with a total of 'n' quests, numbered 
// from 0 to n–1. Some quests have special unlock conditions: certain quests must be 
// completed before others can be attempted. You’re provided with 'm' questDependencies 
// where each element questDependencies[i] = [ai, bi] means that you must finish 
// quest ai before you can start quest bi.

// For instance, the dependency [0, 1] implies that quest 0 must be cleared before 
// quest 1 becomes available. These requirements can also be indirect; if quest 'a' 
// unlocks quest 'b', and quest 'b' unlocks quest 'c', then quest 'a' is considered 
// an indirect prerequisite for quest 'c'.

// In addition, you receive an array called inquiries of size 'k' where each inquiry 
// inquiries[j] = [uj, vj] asks whether quest uj is a necessary precursor to quest vj. 
// Your task is to return a boolean array result where each result[j] answers the 
// jth inquiry.

// Input format:
// -------------
// Line 1: Three space separated integers, representing n, m and k
// Line 2: next m lines of dependencies
// Line 3: next k lines of queries

// Output format:
// --------------
// Resultant boolean array.

// Example 1:
// ----------
// Input=
// 2 1 2
// 1 0
// 0 1
// 1 0
  
// Output= 
// [false, true]  

// Explanation: The dependency [1, 0] indicates that you must complete quest 1 
// before attempting quest 0. Hence, quest 0 is not a prerequisite for quest 1, 
// but quest 1 is for quest 0.

// Example 2:
// ----------
// Input=
// 2 0 2
// 1 0
// 0 1

// Output=
// [false, false]
  
// Explanation: With no dependencies, each quest stands alone.

// Example 3:
// ----------
// Input=
// 3 2 2
// 1 2
// 2 0
// 1 0
// 1 2

// Output=
// [true, true]


// Constraints:
// • 2 <= n <= 100  
// • 0 <= m <= (n * (n - 1) / 2)  
// • Each questDependencies[i] contains exactly 2 elements  
// • 0 <= ai, bi <= n - 1, with ai!= bi  
// • All pairs [ai, bi] are unique  
// • The dependency structure does not contain cycles  
// • 1 <= k <= 10^4 
// • 0 <= uj, vj <= numMissions - 1


import java.util.*;

public class QuestDependencies {
    
    public static boolean helper(Map<Integer,Set<Integer>> adj,int a,int b){
        if(!adj.containsKey(a)) return false;
        if(adj.get(a).contains(b)) return true;
        for(int i : adj.get(a) ) {
            if(helper(adj,i,b)) return true;
        }
        return false;
    }

    public static boolean[] topoBFS(ArrayList<int[]> al,ArrayList<int[]> queries,int n,int m,int k){
        Map<Integer,Set<Integer>> adj = new HashMap<>();
        for(int[] i : al){
            adj.putIfAbsent(i[0],new HashSet<Integer>());
            adj.get(i[0]).add(i[1]);
        }
        boolean[] bool = new boolean[k];
        for(int i=0;i<k;i++){
            bool[i] = helper(adj,queries.get(i)[0],queries.get(i)[1]);
        }
        return bool;
    }

    
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        ArrayList<int[]> al = new ArrayList<>();
        ArrayList<int[]> queries = new ArrayList<>();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        for (int i = 0; i < m; i++) {
            int arr[] = new int[2];
            for (int j = 0; j < 2; j++) {
                arr[j] = sc.nextInt();
            }
            al.add(arr);
        }
        for (int i = 0; i < k; i++) {
            int arr[] = new int[2];
            for (int j = 0; j < 2; j++) {
                arr[j] = sc.nextInt();
            }
            queries.add(arr);
        }
        System.out.println(Arrays.toString(topoBFS(al,queries, n, m,k)));
        sc.close();
    }
}

