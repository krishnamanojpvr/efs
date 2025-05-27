/*
d) Write a JAVA Program to implement a segment tree with its operations In Hyderabad 
after a long pandemic gap, the Telangana Youth festival Is Organized at HITEX. 
In HITEX, there are a lot of programs planned. During the festival in order to maintain the rules 
of Pandemic, they put a constraint that one person can only attend any one of the programs in one 
day according to planned days. Now it’s your aim to implement the "Solution" class in such a way 
that you need to return the maximum number of programs you can attend according to given 
constraints. 
Explanation: You have a list of programs ‘p’ and days ’d’, where you can attend only one program 
on one day. Programs [p] = [first day, last day], p is the program's first day and the last day.
*/

import java.util.*;

public class Exp_1d {

    static class SegmentTree {
        int[] tree;
        int size;

        SegmentTree(int n) {
            size = n;
            tree = new int[4 * n]; // Safe size
            build(0, 0, n - 1);
            System.out.println(Arrays.toString(tree)); // Optional: view initial tree
        }

        void build(int node, int start, int end) {
            if (start == end) {
                tree[node] = start;
            } else {
                int mid = (start + end) / 2;
                build(2 * node + 1, start, mid);
                build(2 * node + 2, mid + 1, end);
                tree[node] = Math.min(tree[2 * node + 1], tree[2 * node + 2]);
            }
        }

        int query(int node, int start, int end, int l, int r) {
            if (r < start || end < l) {
                return Integer.MAX_VALUE;
            }
            if (l <= start && end <= r) {
                return tree[node];
            }
            int mid = (start + end) / 2;
            int left = query(2 * node + 1, start, mid, l, r);
            int right = query(2 * node + 2, mid + 1, end, l, r);
            return Math.min(left, right);
        }

        void update(int node, int start, int end, int idx) {
            if (start == end) {
                tree[node] = Integer.MAX_VALUE;
            } else {
                int mid = (start + end) / 2;
                if (idx <= mid) {
                    update(2 * node + 1, start, mid, idx);
                } else {
                    update(2 * node + 2, mid + 1, end, idx);
                }
                tree[node] = Math.min(tree[2 * node + 1], tree[2 * node + 2]);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine(); // Consume the newline

        int[][] programs = new int[n][2];
        int maxDay = 0;

        for (int i = 0; i < n; i++) {
            programs[i][0] = sc.nextInt();
            programs[i][1] = sc.nextInt();
            maxDay = Math.max(maxDay, programs[i][1]);
        }

        Arrays.sort(programs, (a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        SegmentTree st = new SegmentTree(maxDay);

        int count = 0;

        for (int[] program : programs) {
            int earliestDay = st.query(0, 0, maxDay - 1, program[0] - 1, program[1] - 1);
            if (earliestDay != Integer.MAX_VALUE) {
                count++;
                st.update(0, 0, maxDay - 1, earliestDay);
            }
        }

        System.out.println(count);
        sc.close();
    }
}
