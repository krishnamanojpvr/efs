// Two brothers want to play a game, 
// The rules of the game are: one player gives two sorted lists of 
// numerical elements and a number (sum). 
// The opponent has to find the closest pair of elements 
// to the given sum.
// -> pair consists of elements from each list

// Please help those brothers to develop a program, that takes 
// two sorted lists as input and return a pair as output.

// Input Format:
// -------------
// size of list_1
// list_1 values
// size of list_2
// list_2 values
// closest number

// Output Format:
// --------------
// comma-separated pair

// Sample Input-1:
// ---------------
// 4
// 1 4 5 7
// 4
// 10 20 30 40
// 32
// Sample Output-1
// ---------------
// 1, 30

// Sample Input-2
// ---------------
// 3
// 2 4 6
// 4
// 5 7 11 13
// 15
// sample output-2
// ---------------
// 2, 13


import java.util.*;
public class Pairs{
    public static int[] pairs(int[] arr,int arr2[], int m,int n,int k){
        int[] res = new int[]{0,0};
        int p1=0;
        int p2 = n-1;
        int min = Integer.MAX_VALUE;
        while(p1<m && p2>=0){
            int sum = arr[p1]+arr2[p2];
            int c = Math.abs(k-sum);
            if(c<min){
                min = c;
                res[0] = arr[p1];
                res[1] = arr2[p2];
            }
            if(sum>k){
                p2--;
            }
            else{
                p1++;
            }
        }
        return res;
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int arr[] = new int[m];
        for(int i=0;i<m;i++){
            arr[i] = sc.nextInt();
        }
        int n = sc.nextInt();
        int arr2[] = new int[n];
        for(int i=0;i<n;i++){
            arr2[i] = sc.nextInt();
        }
        int k = sc.nextInt();
        int res[] = pairs(arr,arr2,m,n,k);
        System.out.println(res[0]+", "+res[1]);
        sc.close();
    }
}