// The laser show at the Lumbini Park is something not to be missed.
// But, as per govt rule they have to follow COVID-19 restrictions.
// The management planning to guide the audience to sit in the seats
// that maximizes the distance to the closest person, in order to
// practice the social distance in the auditorium.

// Please help usher to guide the audience to sit in a seat by following few rules:

// - There are N seats in a single row, seats are numbered from 0 to N-1.
// - Maximize the distance from person to the closest person.
// - If there are multiple seats with similar distance, they sit in the seat with the lowest number.
// - First person always sit in seat number 0.
// - If a person leaves the auditorium from a seat, please consider that the seat is vacant

// Create a class Auditorium and two functions in it.
// 1. int seat(): represent the seat number of audience to sit.
// 2. void leave(int s): person leaves the auditorium from a seat number 's'.

// Input Format:
// ----------------
// Line-1 -> two integers N and P, Number of seats N, Number of Operations P
// P lines of input -> each line contains funtion number and parameter list (if required).

// Output Format:
// ------------------
// Print the alloted seat numbers in one line as output.


// Sample Input-1:
// -------------------
// 10 6
// 1
// 1
// 1
// 1
// 2 4
// 1

// Sample Output-1:
// ---------------------
// 0 9 4 2 5

// NOTE:
// -----
// In the sample input:
//     - option 1 indicates, calling "int seat()" method.
//     - option 2 indicates, calling "void leave(seat_num)" method.
    

import java.util.*;
import java.util.*;

class Auditorium {
    private int n;
    private TreeSet<Integer> occupied;

    public Auditorium(int n) {
        this.n = n;
        this.occupied = new TreeSet<>();
    }

    public int seat() {
        if (occupied.isEmpty()) {
            occupied.add(0);
            return 0;
        }

        int maxDist = occupied.first();  
        int seatToSit = 0;

        Integer prev = null;
        for (Integer curr : occupied) {
            if (prev != null) {
                int dist = (curr - prev) / 2;
                int candidate = prev + dist;
                if (dist > maxDist) {
                    maxDist = dist;
                    seatToSit = candidate;
                }
            }
            prev = curr;
        }

        if ((n - 1 - occupied.last()) > maxDist) {
            seatToSit = n - 1;
        }

        occupied.add(seatToSit);
        return seatToSit;
    }

    public void leave(int s) {
        occupied.remove(s);
    }
}

public class ExamRoom{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int arr[][] = new int[k][2];
        for(int i=0;i<k;i++){
            int opt = sc.nextInt();
            arr[i][0] = opt;
            if(opt==2){
                arr[i][1] = sc.nextInt();
            }
        }
        List<Integer> res = new ArrayList<>();
        Auditorium audi = new Auditorium(n);
        for(int i=0;i<k;i++){
            if(arr[i][0]==1){
                res.add(audi.seat());
            }
            else if(arr[i][0]==2){
                audi.leave(arr[i][1]);
            }
        }
        System.out.println(res);
    }
}