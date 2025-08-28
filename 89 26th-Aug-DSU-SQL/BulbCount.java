// There are a series of bulbs numbered from 1 to n and initially all bulbs are 
// turned off.

// Now there are n istructions to be followed sequentially which are represented 
// in an array a[],where a[i] is the value where you have to turn on a[i]-th bulb
// in the series.

// After every instructions you need to find a contiguous series of bulbs which 
// are turned on such that it cannot be extended in either direction.

// Given an integer k return the recent instruction at which there exists exactly 
// 'k'contiguous series of bulbs. If no such series exists, return -1.

// Input Format:
// -------------
// Line-1: an integer n represents the number of instructions
// Line-2: n space seperated integers represents instructions to be followed sequentially.
// Line-3: An integer k.

// Output Format:
// --------------
// return an integer represents recent instruction number.

// Sample Input-1:
// ---------------
// 5
// 3 5 1 2 4
// 1

// Sample Output-1:
// ----------------
// 4

// Explanation:
// ------------
// Step 1: "00100", sets: ["1"]
// Step 2: "00101", sets: ["1", "1"]
// Step 3: "10101", sets: ["1", "1", "1"]
// Step 4: "11101", sets: ["111", "1"]
// Step 5: "11111", sets: ["11111"]
// The recent step at which there exists a group of size 1 is step 4.


// Sample Input-2:
// ---------------
// 5
// 3 1 5 4 2
// 2

// Sample Output-2:
// ----------------
// -1

// Explanation:
// -------------
// Step 1: "00100", sets: ["1"]
// Step 2: "10100", sets: ["1", "1"]
// Step 3: "10101", sets: ["1", "1", "1"]
// Step 4: "10111", sets: ["1", "111"]
// Step 5: "11111", sets: ["11111"]
// No group of size 2 exists during any step.


import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class BulbCount {
    static int[] parent, count;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n+1];
        for(int i=1; i<=n; i++) arr[i] = sc.nextInt();
        int k = sc.nextInt();

        parent = new int[n+2];  
        count = new int[n+2];
        Arrays.fill(parent, -1);

        Map<Integer,Integer> freq = new HashMap<>(); 
        Set<Integer> set = new HashSet<>();

        int res = -1;

        for(int i=1; i<=n; i++) {
            int bulb = arr[i];

            parent[bulb] = bulb;
            count[bulb] = 1;
            freq.put(1, freq.getOrDefault(1,0)+1);

            if(set.contains(bulb-1)) {
                union(bulb-1, bulb, freq);
            }
            if(set.contains(bulb+1)) {
                union(bulb, bulb+1, freq);
            }

            set.add(bulb);

            if(freq.getOrDefault(k,0) > 0) {
                res = i;
            }
        }
        System.out.println(res);
    }

    public static int find(int x) {
        if(parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public static void union(int a, int b, Map<Integer,Integer> freq) {
        int x = find(a);
        int y = find(b);
        if(x == y) return;

        freq.put(count[x], freq.get(count[x]) - 1);
        if(freq.get(count[x]) == 0) freq.remove(count[x]);
        freq.put(count[y], freq.get(count[y]) - 1);
        if(freq.get(count[y]) == 0) freq.remove(count[y]);

        if(count[x] < count[y]) {
            parent[x] = y;
            count[y] += count[x];
            freq.put(count[y], freq.getOrDefault(count[y],0)+1);
        } else {
            parent[y] = x;
            count[x] += count[y];
            freq.put(count[x], freq.getOrDefault(count[x],0)+1);
        }
    }
}
