package timer;

import com.google.common.util.concurrent.RateLimiter;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huqinsong
 * @date 2018/9/25
 */
public class Test {

    private static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(10);
        for (int i =0; i< 100; i++){
            double acquire = rateLimiter.acquire();
            logger.info("=>"+i+",acquire="+acquire);
        }
    }
}
