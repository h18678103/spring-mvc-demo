import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author huqinsong
 * @date 2018/5/2
 */
public class AtomicIntegerTest {
    public static void main(String[] args) {
        AtomicInteger lastNodeIndex = new AtomicInteger(1);
        for (int i=0; i<10; i++){
            System.out.println("i="+i+","+lastNodeIndex.getAndIncrement());
        }
        System.out.println(lastNodeIndex.get());
        System.out.println(lastNodeIndex.getAndIncrement());
    }
}
