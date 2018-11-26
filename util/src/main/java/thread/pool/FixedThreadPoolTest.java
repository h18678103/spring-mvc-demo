package thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author huqinsong
 * @date 2018/11/26
 */
public class FixedThreadPoolTest {
    public static void main(String[] args) {
        // 获取计算机有几个核
        int processors = Runtime.getRuntime().availableProcessors();

        // 第一种线程池:固定个数的线程池,可以为每个CPU核绑定一定数量的线程数
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(processors * 5);

        for (int i = 0; i < 100; i++) {
            fixedThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
        }
        fixedThreadPool.shutdown();
    }
}
