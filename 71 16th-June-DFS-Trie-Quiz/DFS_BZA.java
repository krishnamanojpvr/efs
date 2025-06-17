// Gopal would like to go on Tour, and planned a schedule.
// Airport authority has created a new way of issuing tickets.
// Gopal purchased a set of airline tickets, 
// each ticket contains the 'departure from' and 'arrival to'.

// Redesign the Gopal's tour schedule in an order.
// Gopal intially must begin his tour from BZA.
// Hence the tour schedule's start point should begin with BZA. 

// You are given a list of flight tickets which Gopal has purchased.
// Your task is to find out and return the tour schedule that has the least 
// lexical order. Gopal must use all the tickets and only once.

// Note:
// ------
// If there are multiple valid schedules, you should return the schedule 
// that has the smallest lexical order when read as a single string. 
// For example, the schedule ["BZA", "LGA"] has a smaller lexical order than ["BZA", "LGB"].

// All airports are represented by three capital letters.
// You may assume all tickets form at least one valid schedule.

// Input Format:
// -------------
// Single Line of tickets separated by comma, and each ticket separated by space, 
// Gopal's flight tickets.

// Output Format:
// --------------
// Print the schedule, which is smallest lexical order of tour schedule.


// Sample Input-1:
// ----------------
// DEL HYD,BZA DEL,BLR MAA,HYD BLR

// Sample Output-1:
// --------------------
// [BZA, DEL, HYD, BLR, MAA]


// Sample Input-2:
// ------------------
// BZA BLR,BZA CCU,BLR CCU,CCU BZA,CCU BLR

// Sample Output-2:
// ------------------
// [BZA, BLR, CCU, BZA, CCU, BLR]

import java.util.*;
public class DFS_BZA{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String arr[] = sc.nextLine().split(",");
        Map<String,ArrayList<String>> map = new HashMap<>();
        for(int i=0;i<arr.length;i++){
            String temp[] = arr[i].split(" ");
            map.putIfAbsent(temp[0],new ArrayList<>());
            map.get(temp[0]).add(temp[1]);
        }
        List<String> res = new ArrayList<>();
        int count[] = new int[]{arr.length};
        dfs(map,res,count,"BZA");
        res.add("BZA");
        List<String> rev = new ArrayList<>();
        for(int i = res.size()-1;i>=0;i--) rev.add(res.get(i));
        System.out.println(rev);
        sc.close();
    }
    public static boolean dfs(Map<String,ArrayList<String>> map, List<String> res,int count[],String src){
        if(count[0]==0){
            return true;
        }
        if(!map.containsKey(src) || (map.containsKey(src) && map.get(src).size()==0)){
            return false;
        }
        List<String> dests = map.get(src);
        Collections.sort(dests);
        for(int i=0;i<dests.size();i++){
            count[0]--;
            String s = dests.get(i);
            dests.remove(i);
            if(dfs(map,res,count,s)){
                res.add(s);
                return true;
            }
            count[0]++;
            dests.add(i,s);
        }
        return false;
    }
}