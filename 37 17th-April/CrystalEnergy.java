// You are given a crystal with an energy level n. Your goal is to discover all the 
// different ways this crystal could have been created by combining smaller shards.

// Each combination must:
// - Use only shards with energy values between 2 and n - 1.
// - Be represented as a list of shard values whose product equals n.
// - Use any number of shards (minimum 2), and the order doesn't matter.

// Your task is to return all unique shard combinations that can multiply together 
// to recreate the original crystal.

// Example 1:
// ---------
// Input:
// 28

// Output:
// [[2, 14], [2, 2, 7], [4, 7]]

// Example 2:
// ----------
// Input:
// 23

// Output:
// []



// Constraints:
// - 1 <= n <= 10^4
// - Only shards with energy between 2 and n - 1 can be used.


import java.util.List;
import java.util.*;
public class CrystalEnergy{
    public static void backtrack(List<List<Integer>> res, ArrayList<Integer> temp, int n,int s){
        if(n==1){
            if(temp.size()>1) res.add(new ArrayList<>(temp));
            return;
        }
        for(int i = s;i<=n;i++){
            if(n%i==0){
                temp.add(i);
                backtrack(res, temp, n/i,i);
                temp.remove(temp.size()-1);
            }
        }
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res,new ArrayList<Integer>(),n,2);
        System.out.println(res);
        sc.close();
    }
}