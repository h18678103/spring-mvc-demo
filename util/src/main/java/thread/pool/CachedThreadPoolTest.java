package thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author huqinsong
 * @date 2018/11/26
 */
public class CachedThreadPoolTest {
    public static void main(String[] args) {
        // 缓存线程池，无上限
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            cachedThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
        }
        cachedThreadPool.shutdown();
    }
}
