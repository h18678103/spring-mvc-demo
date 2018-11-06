package timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author huqinsong
 * @date 2018/9/21
 */
public class TimerTest {

    public static void main(String[] args) {
        Timer timer = new Timer();
        //延迟1000ms执行程序
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("IMP 当前时间" + this.scheduledExecutionTime());
            }
        }, 0, 1000);
        //延迟10000ms执行程序
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("IMP 当前时间" + this.scheduledExecutionTime());
            }
        }, new Date(System.currentTimeMillis() + 10000));
    }
}
