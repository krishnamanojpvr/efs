// Cliff Shaw is working on the singly linked list.
// He is given a list of boxes arranged as singly linked list,
// where each box is printed a positive number on it.

// Your task is to help Mr Cliff to find the given list is equivalent to 
// the reverse of it or not. If yes, print "true", otherwise print "false"

// Input Format:
// -------------
// Line-1: space separated integers, boxes as list.

// Output Format:
// --------------
// Print a boolean a value.

// Sample Input-1:
// ---------------
// 3 6 2 6 3

// Sample Output-1:
// ----------------
// true

// Sample Input-2:
// ---------------
// 3 6 2 3 6

// Sample Output-2:
// ----------------
// False

import java.util.Scanner;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class PalindromeLinkedList {

    public static boolean checkIsPalindrome(Node head) {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node reverseMid = reverseHalf(slow);
        Node p1 = head, p2 = reverseMid;
        while (p2 != null) {
            if (p1.data != p2.data)
                return false;
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }

    public static Node reverseHalf(Node head) {
        Node prev = null, curr = head;
        while (curr != null) {
            Node nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        return prev;
    }

    public static Node addNode(String[] arr, int ind) {
        if (ind >= arr.length)
            return null;
        Node head = new Node(Integer.parseInt(arr[ind]));
        head.next = addNode(arr, ind + 1);
        return head;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        Node head = addNode(s, 0);
        System.out.println(checkIsPalindrome(head));
        sc.close();
    }
}
