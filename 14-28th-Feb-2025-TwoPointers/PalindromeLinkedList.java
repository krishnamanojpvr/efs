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
// false

import java.util.*;
class List{
    int data;
    List next=null;
    List(int data){
        this.data = data;
        this.next=null;
    }
}
public class PalindromeLinkedList{
    public static List buildList(String[] arr,int index){
        if(index>=arr.length) return null;
        List head = new List(Integer.parseInt(arr[index]));
        head.next = buildList(arr,index+1);
        return head;
    }
    public static List reverseMid(List head){
        List slow=head,fast=head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        List curr = slow.next;
        List prev = null;
        while(curr!=null){
            List next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
        
    }
    public static boolean isPalindrome(List head){
        List p1 = head;
        List p2 = reverseMid(head);
        while(p1!=null && p2!=null){
            if(p1.data!=p2.data) return false;
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String[] ll = sc.nextLine().split(" ");
        List head = buildList(ll,0);
        System.out.println(isPalindrome(head));
        sc.close();
    }
}