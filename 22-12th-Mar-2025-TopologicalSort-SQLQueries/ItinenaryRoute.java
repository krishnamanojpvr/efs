// Imagine you are designing a treasure hunt that takes participants through 
// n unique landmarks, each identified by a distinct number from 1 to n. 
// The official treasure map provides an itinerary—called the route—which is a 
// complete ordering (a permutation) of these landmarks. Along the way, m clues 
// are given in the form of several smaller landmark sequences. Each clue is a pair
// of landmarks that appear in the route in the same order, though not necessarily 
// consecutively.

// Your challenge is to determine whether the provided route is both the shortest 
// possible itinerary that incorporates all the clues and the only such itinerary. 
// The shortest itinerary is defined as one that contains every clue as an ordered 
// subsequence while including no extra landmarks beyond what is necessary. Although
// there might be multiple itineraries that include all clues, the route must be 
// unique and minimal for your treasure hunt to be considered well-defined.

// Display valid if the official route is the only shortest itinerary that fits all 
// the clues, or invalid otherwise.


// Input format:
// -------------
// Line 1: n m, space seperated 2 integers
// Line 2: n space seperated integers, representing official route
// Line 3: m lines of clues, each clue has 2 space separated integers

// Output format:
// ---------------
// official route is valid or official route is invalid.


// Example 1:  
// input=
// 3 2
// 1 3 2
// 1 2
// 1 3
// output=
// [1, 3, 2] is invalid

// Explanation: 
// - There are two possible itineries: [1,2,3] and [1,3,2].
// - The clue [1,2] is a subsequence of both: [1,2,3] and [1,3,2].
// - The clue [1,3] is a subsequence of both: [1,2,3] and [1,3,2].
// - Since there are multiple valid shortest itineraries, the official route is not unique.

// Example 2: 
// input=
// 3 1
// 1 2 3
// 1 2
// output=
// [1, 2, 3] is invalid

// Explanation: 
// - The shortest possible itinerary is [1,2].
// - The clue [1,2] is a subsequence of it: [1,2].
// - Since official route is not the shortest itinerary, we return false.

// Example 3:
// input=
// 3 3
// 1 2 3
// 1 2
// 1 3
// 2 3
// Output=
// [1, 2, 3] is valid
  
// Explanation: 
// - The only possible shortest itinerary that contains all the clues is [1, 2, 3]. 
// - No alternative minimal route exists. 

import java.util.*;

public class ItinenaryRoute {

    public static boolean topoBFS(ArrayList<int[]> al,int n,int m,ArrayList<Integer> route){
        Map<Integer,ArrayList<Integer>> adj = new HashMap<>();
        Map<Integer,Integer> indeg = new HashMap<>();
        for(int[] i : al){
            adj.putIfAbsent(i[0],new ArrayList<Integer>());
            adj.get(i[0]).add(i[1]);
            indeg.put(i[1],indeg.getOrDefault(i[1],0)+1);
        }
        Queue<Integer> q = new LinkedList<>();
        ArrayList<Integer> res = new ArrayList<>();
        for(int i=1;i<=n;i++){
            if(!indeg.containsKey(i)) q.offer(i);
        }
        if(q.size()>1) return false;
        while(!q.isEmpty()){
            int independent = q.poll();
            if(q.size()>1) return false;
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
        return res.equals(route);
    }

    
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        ArrayList<int[]> al = new ArrayList<>();
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<Integer> route = new ArrayList<>();
        for(int i=0;i<n;i++){
            route.add(sc.nextInt());
        }
        for (int i = 0; i < m; i++) {
            int arr[] = new int[2];
            for (int j = 0; j < 2; j++) {
                arr[j] = sc.nextInt();
            }
            al.add(arr);
        }
        System.out.println(topoBFS(al, n, m,route)?route + "is valid":route + "is invalid");
        sc.close();
    }
}
