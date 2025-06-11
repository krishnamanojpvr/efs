// You write a love letter to your friend. However, before your friend can read it, 
// someone else takes it and rotates the characters of each word from left to right 
// K times.

// Your task is to determine how many of the words still remain the same even after 
// this rotation.

// Input Format:
// -------------
// input1: A string containing words separated by spaces.
// input2: An integer K indicating the number of right rotations for each word.

// Output Format:
// --------------
// An integer representing the number of words that remain unchanged after K right 
// rotations.


// Sample Input: 
// -------------
// ramram santan nnnn
// 3

// Sample Output:
// --------------
// 2


// Sample Input: 
// -------------
// adasda
// 3

// Sample Output:
// --------------
// 0

import java.util.*;
public class RotateChars{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String input[] = sc.nextLine().split(" ");
        int n = sc.nextInt();
        int count = 0;
        for(String s : input){
            int r = n%s.length();
            // StringBuilder sb = new StringBuilder(s);
            // for(int i=0;i<r;i++){
            //     char last = sb.charAt(0);
            //     sb.deleteCharAt(0);
            //     sb.append(last);
            // }
            StringBuilder sb = new StringBuilder(s.substring(r));
            sb.append(s.substring(0,r));
            count+=s.equals(sb.toString())?1:0;
        }
        System.out.println(count);
        sc.close();
    }
}