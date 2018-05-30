import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author huqinsong
 * @date 2018/5/29
 */
public class TTTTT {

    public static void main(String[] args) {
//        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
//        scheduler.scheduleWithFixedDelay(() -> System.out.println("xxx"), 2, 2, TimeUnit.SECONDS);
        byte[] bytes = "a".getBytes();
        for (byte b: bytes){
            System.out.println(b);
        }
    }
}
