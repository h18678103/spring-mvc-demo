package jdbc;

/**
 * @author huqinsong
 * @date 2018/7/16
 */
public class MyThread extends Thread {

    DBAdd add;

    public MyThread(DBAdd add) {
        this.add = add;
    }

    @Override
    public void run() {
        for (int i = 0; i<100; i++){
            add.add();
            System.out.println(Thread.currentThread().getName()+"<=add");
        }
        add.countDownLatch.countDown();
    }
}
