import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author huqinsong
 * @date 2018/3/30
 */
public class TimeTEst {
    private static final SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
    private static final SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

    public static void main(String[] args) throws ParseException {
        Date parse = df.parse("05:00:00");
        System.out.println(df2.format(parse));
        System.out.println(parse.getTime());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(df2.parse("1970-01-01 05:00:00 850"));
        Date date1 = calendar.getTime();
        System.out.println(date1.getTime());
        System.out.println(df2.format(date1));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date2 = calendar.getTime();
        System.out.println(df2.format(date2));
        System.out.println(date2.getTime());
    }
}
