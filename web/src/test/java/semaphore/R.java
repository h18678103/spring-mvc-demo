package semaphore;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huqinsong
 * @date 2019/3/18
 */
public class R implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(R.class);
    private Semaphore semaphore;
    private CountDownLatch latch;

    public R(Semaphore semaphore, CountDownLatch latch) {
        this.semaphore = semaphore;
        this.latch = latch;
    }

    @Override
    public void run() {
        log.info(Thread.currentThread().getName()+" is running!");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
            latch.countDown();
            log.info(Thread.currentThread().getName()+" released!"+",semaphoreCount="+semaphore.availablePermits()+",latch="+latch.getCount());
        }
    }
}
