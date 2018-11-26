package old;

import java.sql.Time;
import java.util.Date;

/**
 * @author huqinsong
 * @date 2018/4/8
 */
public class TimeTest2 {

    public static void main(String[] args) {
        Date date = new Date();
        long time2 = date.getTime();
        System.out.println(time2);
        java.sql.Time time = new Time(date.getTime());
        long time1 = time.getTime();
        System.out.println(time1);
    }
}
