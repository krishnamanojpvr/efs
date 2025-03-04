// You are a database integrity engineer working for a global cloud company. 
// Your team maintains a distributed database network, where each server either:
//     - Stores equivalent data to another server (serverX == serverY).
//     - Stores different data from another server (serverX != serverY).

// The transitive consistency rule must be followed:
//     - If A == B and B == C, then A == C must be true.
//     - If A == B and B != C, then A != C must be true.

// Your task is to analyze the given constraints and determine whether they 
// follow transitive consistency. If all relations are consistent, return true; 
// otherwise, return false

// Input Format:
// -------------
// Space separated strnigs, list of relations

// Output Format:
// --------------
// Print a boolean value, whether transitive law is obeyed or not.


// Sample Input-1:
// ---------------
// a==b c==d c!=e e==f

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// a==b b!=c c==a

// Sample Output-2:
// ----------------
// false

// Explanation:
// ------------
// {a, b} form one equivalence group.
// {c} is declared equal to {a} (c == a), which means {a, b, c} should be equivalent.
// However, b != c contradicts b == a and c == a.

// Sample Input-3:
// ---------------
// a==b b==c c!=d d!=e f==g g!=d

// Sample Output-3:
// ----------------
// true


import java.util.*;
public class CityRegionsII{
    public static int[] alphabetArr(){
        int arr[] = new int[26];
        for(int i=0;i<26;i++){
            arr[i] = i;
        }
        return arr;
    }
    public static boolean solve(String[] input){
        ArrayList<String> neq = new ArrayList<>();
        int[] parent = alphabetArr();
        int[] rank = alphabetArr();
        for(int i=0;i<input.length;i++){
            if(input[i].charAt(1)!='!'){
                union(parent,rank,input[i].charAt(0)-'a',input[i].charAt(3)-'a');
            }
            else{
                neq.add(input[i]);
            }
        }
        for(String s : neq){
            if(find(parent,s.charAt(0)-'a') == find(parent,s.charAt(3)-'a') ) return false;
        }
        return true;
    }
    public static void union(int[] parent,int[] rank,int a,int b){
        int x = find(parent,a);
        int y = find(parent,b);
        if(x==y) return;
        if(rank[x]>rank[y]){
            parent[x] = y;
        }
        else if(rank[y]>rank[x]){
            parent[y] = x;
        }
        return;
    }
    public static int find(int[] parent,int c){
        if(parent[c]!=c){
            parent[c] = find(parent,parent[c]);
        }
        return parent[c];
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String input[] = sc.nextLine().split(" ");
        System.out.println(solve(input));
        sc.close();
    }
}