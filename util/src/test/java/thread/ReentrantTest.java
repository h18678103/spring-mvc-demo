package thread;

public class ReentrantTest implements Runnable {
    public synchronized void get() {
        System.out.println(Thread.currentThread().getName());
        set();
    }
    public synchronized void set() {
        System.out.println(Thread.currentThread().getName());
    }
    public void run() {
        get();
    }
    public static void main(String[] args) {
        ReentrantTest rt = new ReentrantTest();
        for(int i = 0; i<5;i++){
            new Thread(rt).start();
        }
    }
}