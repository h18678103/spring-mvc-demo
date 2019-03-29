package semaphore;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huqinsong
 * @date 2019/3/18
 */
public class T {
    private static Logger log = LoggerFactory.getLogger(T.class);

    public static void main(String[] args) {
        // 计时
        long st = System.currentTimeMillis();

        Semaphore semaphore = new Semaphore(2);
        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new R(semaphore, latch));
            try {
                log.info(Thread.currentThread().getName() + " is waiting!");
                semaphore.acquire();
                log.info(Thread.currentThread().getName() + " go!go!go!");
                t.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long cost = (System.currentTimeMillis() - st) / 1000;
        log.info("完成，耗时={}s", cost);
    }
}
