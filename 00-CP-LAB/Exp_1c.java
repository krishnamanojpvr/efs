/*
c) Write a JAVA Program to implement Fenwick Tree 
Malika taught a new fun time program practice for Engineering Students. As a part of this she 
has given set of numbers, and asked the students to find the gross value of numbers between 
indices S1 and S2 (S1<=S2), inclusive. Now it’s your task to create a method sumRange(S1,S2) 
which return the sum of numbers between the indices S1 and S2, both are inclusive. 
 */

import java.util.*;

class FenwickTree {
    int size;
    int bit[];

    FenwickTree(int size) {
        this.size = size;
        this.bit = new int[size + 1];
    }

    public void update(int index, int val) {
        while (index <= size) {
            bit[index] += val;
            index += (index & (-index));
        }
    }

    public int query(int index) {
        int sum = 0;
        while (index > 0) {
            sum += bit[index];
            index -= (index & (-index));
        }
        return sum;
    }

    public int sumRange(int i, int j) {
        return query(j) - query(i - 1);
    }

}

public class Exp_1c {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        FenwickTree ft = new FenwickTree(n);
        for (int i = 1; i <= n; i++)
            ft.update(i, nums[i-1]);
        int s1 = sc.nextInt();
        int s2 = sc.nextInt();
        System.out.println(ft.sumRange(s1, s2));
        sc.close();
    }

}