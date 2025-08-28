// A wall clock is a complete whole circle and cover 360°.
// You are given the time as HH:MM.
// Your task is to return the angle between the Hours hand and Minutes hand
// of the clock and the angle should not be reflex angle.

// Input Format:
// -------------
// A string time, HH:MM

// Output Format:
// --------------
// Print a double result, within 10^-5 of the original value.


// Sample Input-1:
// ---------------
// 04:30

// Sample Output-1:
// ----------------
// 45.00000


// Sample Input-2:
// ---------------
// 06:15

// Sample Output-2:
// ----------------
// 97.50000

import java.util.*;
public class AnalogClock{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String arr[] = sc.nextLine().split(":");
        double hr = (double)("12".equals(arr[0])?0:(5*Integer.parseInt(arr[0])));
        double min = (double)Integer.parseInt(arr[1]);
        double newHr = hr +(min/12);
        double angle = 6*Math.abs(newHr-min);
        System.out.println(Math.min(angle,360-angle));
    }
}