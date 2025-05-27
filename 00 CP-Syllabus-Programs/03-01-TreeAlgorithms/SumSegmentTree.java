import java.util.*;

//* Sum Segment Tree
class Segment {
    int[] tree;
    int[] lazy;
    int n;

    Segment(int n) {
        this.tree = new int[4 * n];
        this.lazy = new int[4 * n];
        this.n = n;
    }

    public void build(int[] arr, int ind, int l, int r) {
        if (l == r) {
            tree[ind] = arr[l];
            return;
        }
        int mid = (l + r) / 2;
        build(arr, 2 * ind + 1, l, mid);
        build(arr, 2 * ind + 2, mid + 1, r);
        tree[ind] = tree[2 * ind + 1] + tree[2 * ind + 2];
    }

    public int pointQuery(int ind, int a, int b, int l, int r) {
        if (a == b) {
            return tree[ind];
        }
        if (b < l || a > r) {
            return Integer.MIN_VALUE;
        }
        int mid = (a + b) / 2;
        int m1 = pointQuery(2 * ind + 1, a, mid, l, r);
        int m2 = pointQuery(2 * ind + 2, mid + 1, b, l, r);
        return m1 + m2;
    }

    public void pointUpdate(int ind, int a, int b, int givenIndex, int val) {
        if (a == b) {
            tree[ind] += val;
            return;
        }
        int mid = (a + b) / 2;
        if (givenIndex <= mid && givenIndex >= a) {
            pointUpdate(2 * ind + 1, a, mid, givenIndex, val);
        } else {
            pointUpdate(2 * ind + 2, mid + 1, b, givenIndex, val);
        }
        tree[ind] = tree[2 * ind + 1] + tree[2 * ind + 2];
    }

    public int rangeQuery(int ind, int a, int b, int l, int r) {
        if (lazy[ind] != 0) {
            tree[ind] += (a - b + 1) * lazy[ind];
            if (a != b) {
                lazy[2 * ind + 1] += lazy[ind];
                lazy[2 * ind + 2] += lazy[ind];
            }
            lazy[ind] = 0;
        }
        if (a > r || b < l || a > b)
            return Integer.MIN_VALUE;
        if (a >= l && b <= r)
            return tree[ind];

        int mid = (a + b) / 2;
        int q1 = rangeQuery(2 * ind + 1, a, mid, l, r);
        int q2 = rangeQuery(2 * ind + 2, mid + 1, b, l, r);
        return q1 + q2;

    }

    public void rangeUpdate(int ind, int a, int b, int l, int r, int val) {
        if (lazy[ind] != 0) {
            tree[ind] += (a - b + 1) * lazy[ind];
            if (a != b) {
                lazy[2 * ind + 1] += lazy[ind];
                lazy[2 * ind + 2] += lazy[ind];
            }
            lazy[ind] = 0;
        }

        if (a > r || b < l || a > b)
            return;

        if (a >= l && b <= r) {
            tree[ind] += (a-b+1)*val;
            if (a != b) {
                lazy[2 * ind + 1] += val;
                lazy[2 * ind + 2] += val;
            }
            return;
        }

        int mid = (a + b) / 2;
        rangeUpdate(2 * ind + 1, a, mid, l, r, val);
        rangeUpdate(2 * ind + 2, mid + 1, b, l, r, val);
        tree[ind] = tree[2 * ind + 1] + tree[2 * ind + 2];

    }
}

public class SumSegmentTree {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        Segment seg = new Segment(n);
        seg.build(arr, 0, 0, n - 1);
        seg.rangeUpdate(0, 0, n - 1, 2, 5, 5);
        System.out.println(seg.rangeQuery(0, 0, n - 1, 0, n - 1));
        sc.close();
    }
}