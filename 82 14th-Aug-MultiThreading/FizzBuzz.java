import java.util.*;
class HHVM{
    int n;
    int current = 1;
    HHVM(int n){
        this.n = n;
    }
    public synchronized void printHi() throws InterruptedException{
        while(current<=n){
            if(current%3==0 && current%5!=0){
                System.out.print("Hi ");
                current++;
                notifyAll();
            }
            else{
                wait();
            }
        }
    }
    public synchronized void printHello() throws InterruptedException{
        while(current<=n){
            if(current%3!=0 && current%5==0){
                System.out.print("Hello ");
                current++;
                notifyAll();
            }
            else{
                wait();
            }
        }
    }
    public synchronized void printHiHello() throws InterruptedException{
        while(current<=n){
            if(current%3==0 && current%5==0){
                System.out.print("HiHello ");
                current++;
                notifyAll();
            }
            else{
                wait();
            }
        }
    }
    public synchronized void printNumber() throws InterruptedException{
       while(current<=n){
            if(current%3!=0 && current%5!=0){
                System.out.print(current+ " ");
                current++;
                notifyAll();
            }
            else{
                wait();
            }
        }
    }
}
public class FizzBuzz{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        HHVM obj = new HHVM(n);
        Thread t1 = new Thread(() -> {
            try{obj.printHi();}
            catch(InterruptedException e){
                System.err.println("T1 Exception");
            }
        });
        Thread t2 = new Thread(() -> {
            try{obj.printHello();}
            catch(InterruptedException e){
                System.err.println("T2 Exception");
            }
        });
        Thread t3 = new Thread(() -> {
            try{obj.printHiHello();}
            catch(InterruptedException e){
                System.err.println("T3 Exception");
            }
        });
        Thread t4 = new Thread(() -> {
            try{obj.printNumber();}
            catch(InterruptedException e){
                System.err.println("T4 Exception");
            }
        });
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        sc.close();
    }
    
}