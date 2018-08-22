package jdbc;

/**
 * @author huqinsong
 * @date 2018/7/16
 */
public class Demo2 {

    static DBAdd add = new DBAdd();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new MyThread(add);
        t1.start();
        Thread t2 = new MyThread(add);
        t2.start();
        Thread t3 = new MyThread(add);
        t3.start();
        add.countDownLatch.await();
    }
}
