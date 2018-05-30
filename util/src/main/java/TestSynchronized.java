/**
 * @author huqinsong
 * @date 2018/4/5
 */
public class TestSynchronized {
    public void test1() {
        synchronized (TestSynchronized.class) {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                }
            }
        }
    }

    public synchronized static void test2() {
        int i = 5;
        while (i-- > 0) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
            }
        }
    }

    public static void main(String[] args) {
        final TestSynchronized myt2 = new TestSynchronized();
        Thread test1 = new Thread(() -> myt2.test1(), "test1");
        Thread test2 = new Thread(() -> test2(), "test2");
        test1.start();
        test2.start();
    }
}
