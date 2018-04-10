import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author huqinsong
 * @date 2018/3/27
 */
public class TimeBetween {
    //设置日期格式
    private static final SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
    public static void main(String[] args) {
        new TimeBetween().isBelong();

        Time time1 = new Time(0);
        long time11 = time1.getTime();
        System.out.println("time11===>"+time11);

        Time time2 = new Time(235959);
        long time22 = time2.getTime();
        System.out.println("time22===>"+time22);

        Calendar calendar = Calendar.getInstance();
        Date date1 = calendar.getTime();
        long t1 = date1.getTime();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date date2 = calendar.getTime();
        long t2 = date2.getTime();
        System.out.println("time1===>"+t1);
        System.out.println("time2===>"+t2);
        System.out.println("time2===>"+(t1-t2));
    }

    public void isBelong(){


        Date now =null;
        Date beginTime = null;
        Date endTime = null;
        try {
            now = df.parse(df.format(new Date()));
            beginTime = df.parse("15:45:59");
            endTime = df.parse("22:00:59");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Boolean flag = belongCalendar(now, beginTime, endTime);
        System.out.println(flag);
    }

    /**
     * 判断时间是否在时间段内
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

}
