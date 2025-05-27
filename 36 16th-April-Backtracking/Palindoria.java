/*
 In the ancient land of Palindoria, wizards write magical spells as strings of 
lowercase letters. However, for a spell to be effective, each segment of the 
spell must read the same forward and backward.

Given a magical spell 'spell', your task is to partition it into sequences of 
valid magical spell segments. 
Your goal is to help the wizard discover all valid combinations of magical spell 
segmentations.

Example 1:
----------
Input:  
aab
  
Output:  
[[a, a, b], [aa, b]]

Example 2:

Input:  
a  
Output:  
[[a]]

Spell Constraints:

- The length of the spell is between 1 and 16 characters.  
- The spell contains only lowercase English letters.  
 */

import java.util.*;
public class Palindoria{
    public static void bt(String spell,ArrayList<ArrayList<String>> res,ArrayList<String> temp,int index,int n){
        if(index==n){
            res.add(new ArrayList<>(temp));
        }
        for(int i=index+1;i<=n;i++){
            String sub = spell.substring(index,i);
            StringBuilder sb = new StringBuilder(sub);
            if(sub.equals(sb.reverse().toString())){
                temp.add(sub);
                bt(spell,res,temp,i,n);
                temp.remove(temp.size()-1);
            }
        }
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String spell = sc.next();
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        bt(spell,res,new ArrayList<>(),0,spell.length());
        System.out.println(res);
        sc.close();
    }
}