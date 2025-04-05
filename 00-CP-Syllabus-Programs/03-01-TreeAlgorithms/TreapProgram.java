import java.util.*;

class TreapNode {
    int key, priority;
    TreapNode left, right;

    TreapNode(int key) {
        this.key = key;
        this.priority = (int) (Math.random() * 100);
        this.left = null;
        this.right = null;
    }
}

class Treap {
    TreapNode root;

    Treap() {
        this.root = null;
    }

    public TreapNode leftRotate(TreapNode root) {
        TreapNode r = root.right;
        TreapNode l = r.left;
        r.left = root;
        root.right = l;
        return r;
    }

    public TreapNode rightRotate(TreapNode root) {
        TreapNode l = root.left;
        TreapNode r = l.right;
        l.right = root;
        root.left = r;
        return l;

    }

    public TreapNode insert(TreapNode root, int key) {
        if (root == null) {
            return new TreapNode(key);
        }
        if (key <= root.key) {
            root.left = insert(root.left, key);
            if (root.left.priority > root.priority) {
                root = rightRotate(root);
            }
        } else {
            root.right = insert(root.right, key);
            if (root.right.priority > root.priority) {
                root = leftRotate(root);
            }
        }
        return root;
    }

    public TreapNode delete(TreapNode root, int key) {
        if (root == null)
            return null;
        else if (key < root.key)
            root.left = delete(root.left, key);
        else if (key > root.key)
            root.right = delete(root.right, key);
        else if (root.left == null) {
            TreapNode temp = root.right;
            root = temp;
        } else if (root.right == null) {
            TreapNode temp = root.left;
            root = temp;
        } else if (root.left.priority < root.right.priority) {
            root = leftRotate(root);
            root.left = delete(root.left, key);
        } else {
            root = rightRotate(root);
            root.right = delete(root.right, key);
        }
        return root;

    }

    public TreapNode search(TreapNode root, int key) {
        if (root == null || root.key == key)
            return root;
        if (key > root.key)
            return search(root.right, key);
        return search(root.left, key);
    }

    public void inorder(TreapNode root) {
        if (root == null)
            return;
        inorder(root.left);
        System.out.println(root.key + "-" + root.priority + "  ");
        inorder(root.right);
    }

}

public class TreapProgram {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        Treap treap = new Treap();
        for (int a : arr) {
            treap.root = treap.insert(treap.root, a);
        }
        treap.inorder(treap.root);
        System.out.println("----------------------------");
        int key = sc.nextInt();
        TreapNode result = treap.search(treap.root, key);
        if (result != null) {
            System.out.println("Search result " + result.key + " " + result.priority);
        }
        System.out.println("----------------------------");
        int ins = sc.nextInt();
        treap.root = treap.insert(treap.root, ins);
        treap.inorder(treap.root);
        System.out.println("----------------------------");
        int del = sc.nextInt();
        treap.root = treap.delete(treap.root, del);
        treap.inorder(treap.root);
        sc.close();
    }
}