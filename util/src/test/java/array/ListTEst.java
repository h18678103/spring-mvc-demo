package array;

import com.google.common.util.concurrent.RateLimiter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huqinsong
 * @date 2018/11/7
 */
public class ListTEst {
    public static void main(String[] args) {
        Map m = new LinkedHashMap();
        m.put(null, 123);
        System.out.println(m.get(null));
        RateLimiter rateLimiter = RateLimiter.create(0.5);

        for(int i = 0; i<100; i++){
            rateLimiter.acquire();
            System.out.println(i);
        }

    }
}
