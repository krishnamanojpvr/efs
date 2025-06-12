// You are building memory management for a smart home hub where apps are loaded on-demand. 
// The hub has limited memory (cache size). If an app is not in memory (cache miss), 
// it is loaded and a page fault occurs. If memory is full, the Least Recently Used 
// (LRU) app is removed.

// Simulate the LRU page replacement and count total page faults.

// Input Format:
// -------------
// - Cache size
// - Space-separated app access sequence (e.g., Thermostat Camera Lights)

// Output Format:
// --------------
// Total page faults
// Final cache contents

// Sample Input:
// -------------
// 3
// Thermostat Camera Lights Thermostat Camera Doorbell Lights Thermostat

// Sample Output:
// --------------
// Total Page Faults: 6
// Final Cache: [Doorbell, Lights, Thermostat]


// Sample Input:
// --------------
// 2
// AC Light Fan AC Heater Light

// Sample Output:
// --------------
// Total Page Faults: 6
// Final Cache: [Heater, Light]

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;


public class LRU{
    public static void main (String[] args) {
        Scanner cin =  new Scanner(System.in);
        int n = cin.nextInt();
        cin.nextLine();
        String inp [] = cin.nextLine().split(" ");
        lru(n,inp);
        cin.close();
    }
    static void lru(int n, String inp []){

        Set<String> cache = new LinkedHashSet<>();
        int misses = 0;
        int len = inp.length;
        for(int i =0; i< len; i++){
            if(cache.contains(inp[i])){
                cache.remove(inp[i]);
                cache.add(inp[i]);
            }
            else{
                misses++;
                if(cache.size()==n){
                    cache.remove(cache.iterator().next());
                }
                cache.add(inp[i]);
            }
        }
        
        System.out.println("Total Page Faults: "+misses);
        System.out.print("Final cache: ");

        System.out.print(cache);
        
    }
}