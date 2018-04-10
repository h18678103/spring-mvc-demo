import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author huqinsong
 * @date 2018/3/27
 */
public class MatcherTest {

    public static void main(String[] args) {
        Short s = 0;
        Integer integer = Integer.valueOf(s);
        System.out.println(integer);
        String o = null;
        long ss  = Long.parseLong(o);
        System.out.println(ss);

        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        boolean oldValue = atomicBoolean.getAndSet(false);

        AtomicInteger atomicInteger = new AtomicInteger(123);
        int theValue = atomicInteger.get();

        ReentrantLock reentrantLock = new ReentrantLock(true);

    }

}
