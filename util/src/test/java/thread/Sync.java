package thread;

/**
 * @author huqinsong
 * @date 2018/7/3
 */
public class Sync {

    public synchronized void test() {
        System.out.println("test开始..");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test结束..");
    }

    public synchronized void test2() {
        System.out.println("test222开始..");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test222结束..");
    }
}

class MyThread extends Thread {

    Sync sync;

    public MyThread(Sync sync) {
        this.sync = sync;
    }

    public void run() {
        sync.test();
        sync.test2();
    }
}

class Main {

    public static void main(String[] args) {
        Sync sync = new Sync();
        for (int i = 0; i < 3; i++) {
            Thread thread = new MyThread(sync);
            thread.start();
        }
    }
}