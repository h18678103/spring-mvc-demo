package thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author huqinsong
 * @date 2018/11/26
 */
public class SingleThreadExecutorTest {
    public static void main(String[] args) {
        // 单一线程池,永远会维护存在一条线程
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 1000; i++) {
            final int j = i;
            singleThreadPool.execute(() -> System.out.println(Thread.currentThread().getName() + ":" + j));
        }
        singleThreadPool.shutdown();

    }
}
