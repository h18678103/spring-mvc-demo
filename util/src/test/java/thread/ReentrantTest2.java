package thread;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantTest2 implements Runnable {
    ReentrantLock lock = new ReentrantLock();

    public synchronized void get() {
        lock.lock();
        System.out.println(Thread.currentThread().getName());
        set();
        lock.unlock();
    }
    public synchronized void set() {
        lock.lock();
        System.out.println(Thread.currentThread().getName());
        lock.unlock();
    }
    public void run() {
        get();
    }
    public static void main(String[] args) {
        ReentrantTest2 rt = new ReentrantTest2();
        for(int i = 0; i<5;i++){
            new Thread(rt).start();
        }
    }
}