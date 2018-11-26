package thread;

/**
 * @author huqinsong
 * @date 2018/11/14
 */
public class TestThreadLocal {

    ThreadLocal<Long> longLocal = ThreadLocal.withInitial(() -> Thread.currentThread().getId());
    ThreadLocal<String> stringLocal = ThreadLocal.withInitial(() -> Thread.currentThread().getName());

    public void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    public long getLong() {
        return longLocal.get();
    }

    public String getString() {
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        final TestThreadLocal test = new TestThreadLocal();

        System.out.println(test.getLong());
        System.out.println(test.getString());

        Thread thread1 = new Thread(() -> {
//            test.set();
            System.out.println(test.getLong());
            System.out.println(test.getString());
        });
        thread1.start();
        thread1.join();

        System.out.println(test.getLong());
        System.out.println(test.getString());
    }
}
