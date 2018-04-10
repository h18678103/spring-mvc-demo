import java.util.concurrent.CountDownLatch;

/**
 * @author huqinsong
 * @date 2018/4/10
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        Thread t = new Thread(() -> {
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println("countDown!!!"+countDownLatch.getCount());
                if (countDownLatch.getCount()==0){
                    break;
                }
            }
        });
        t.start();
        System.out.println("await start!!!!");
        countDownLatch.await();
        System.out.println("await over!!!!");
    }
}
