
import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

class O extends Thread {

    CyclicBarrier cb;
    Semaphore sem;

    O(CyclicBarrier cb, Semaphore sem) {
        this.cb = cb;
        this.sem = sem;
    }

    @Override
    public void run() {
        try {
            sem.acquire();
            cb.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        } finally {
            sem.release();
        }
    }
}

class N extends Thread {

    CyclicBarrier cb;
    Semaphore sem;

    N(CyclicBarrier cb, Semaphore sem) {
        this.cb = cb;
        this.sem = sem;
    }

    @Override
    public void run() {
        try {
            sem.acquire();
            cb.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        } finally {
            sem.release();
        }
    }
}

public class SemaphoreExample {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        sc.close();
        int cn = 0;
        int co = 0;
        for (char c : s.toCharArray()) {
            if (c == 'N') {
                cn++;
            } else if (c == 'O') {
                co++;
            }
        }
        if (cn != co * 2) {
            System.out.println("invalid");
            return;
        }
        AtomicInteger phase = new AtomicInteger(1);
        CyclicBarrier cb = new CyclicBarrier(3, () -> {
            System.out.println("N20 - " + phase.getAndIncrement() + " formed");
        });

        Semaphore semo = new Semaphore(1);
        Semaphore semn = new Semaphore(2);

        for (int i = 0; i < co; i++) {
            new O(cb, semo).start();
        }

        for (int i = 0; i < cn; i++) {
            new N(cb, semn).start();
        }

    }

}
