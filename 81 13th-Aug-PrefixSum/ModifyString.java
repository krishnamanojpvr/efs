// Somesh is given a task to you on Strings.
// You have given a string S ([a-z]), and an integer array Arr[]
// Now your task is to modify 'S' in such way:
// replace the 'i+1' characters in the string, with next i-th character 
// in alphabetic order(cyclic).

// For example, if S="art", Arr[]=[2,3,5] is 3, 
// i=0, modify('a') = 'c' , S="crt".
// i=1, modify('c') = 'f', modify('r') = 'u', S="fut".
// i=2, modify('f') = 'k', modify('u') = 'z', modify('t') = 'y', S="kzy"
// Finally modified string is kzy. 
// Note: if arr[i]=3 modify('z') ='c'
// Return the final modified string after all such modifications to S are applied.
// Input Format:
// -------------
// Line-1 -> A String S, length of S is L
// Line-2 -> L space separated integers.
// Output Format:
// --------------
// Print modifed String.
// Sample Input-1:
// ---------------
// adbp
// 1 2 3 4
// Sample Output-1:
// ----------------
// kmit
import java.util.Scanner;

public class ModifyString {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int n = s.length();
        int arr[] = new int[n];
        char c[] = s.toCharArray();
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int suffixSum = 0;
        for (int i : arr) {
            suffixSum += i;
        }
        for (int i = 0; i < n; i++) {
            int x = c[i] - 'a';
            x = (x + suffixSum) % 26;
            c[i] = (char) (x+'a');
            suffixSum -= arr[i];
        }
        System.out.println(new String(c));
        sc.close();
    }

    public static void bruteforce() {
        String abcd = "abcdefghijklmnopqrstuvwxyz";
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int n = s.length();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        char charr[] = new char[n];
        for (int i = 0; i < n; i++) {
            charr[i] = s.charAt(i);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                int x = charr[j] - 'a';
                x = (x + arr[i]) % 26;
                charr[j] = abcd.charAt(x);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(charr[i]);
        }
        System.out.println(sb);
        sc.close();
    }
}
