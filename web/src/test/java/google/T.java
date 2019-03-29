package google;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author huqinsong
 * @date 2019/3/27
 */
public class T {
    public static void main(String[] args) throws ParseException {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = sdf.parse("20190415");
        System.out.println(sdf.format(date));
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, 128);
        System.out.println(sdf.format(c.getTime()));
    }
}
