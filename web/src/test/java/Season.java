import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * 立春	2月4日 05:28:25	雨水	2月19日 01:17:57	惊蛰	3月5日 23:28:06
 春分	3月21日 00:15:24	清明	4月5日 04:12:43	谷雨	4月20日 11:12:29
 立夏	5月5日 21:25:18	小满	5月21日 10:14:33	芒种	6月6日 01:29:04
 夏至	6月21日 18:07:12	小暑	7月7日 11:41:47	大暑	7月23日 05:00:16
 立秋	8月7日 21:30:34	处暑	8月23日 12:08:30	白露	9月8日 00:29:37
 秋分	9月23日 09:54:01	寒露	10月8日 16:14:37	霜降	10月23日 19:22:18
 立冬	11月7日 19:31:39	小雪	11月22日 17:01:24	大雪	12月7日 12:25:48
 冬至	12月22日 06:22:38	小寒	1月5日 17:48:41	大寒	1月20日 11:08:58
 * @author huqinsong
 * @date 2019/1/7
 */
public class Season {
    
    private static String s = "立春 2月4日 05:28:25 雨水 2月19日 01:17:57 惊蛰 3月5日 23:28:06 " +
            "春分 3月21日 00:15:24 清明 4月5日 04:12:43 谷雨 4月20日 11:12:29 " +
            "立夏 5月5日 21:25:18 小满 5月21日 10:14:33 芒种 6月6日 01:29:04 " +
            "夏至 6月21日 18:07:12 小暑 7月7日 11:41:47 大暑 7月23日 05:00:16 " +
            "立秋 8月7日 21:30:34 处暑 8月23日 12:08:30 白露 9月8日 00:29:37 " +
            "秋分 9月23日 09:54:01 寒露 10月8日 16:14:37 霜降 10月23日 19:22:18 " +
            "立冬 11月7日 19:31:39 小雪 11月22日 17:01:24 大雪 12月7日 12:25:48 " +
            "冬至 12月22日 06:22:38 小寒 1月5日 17:48:41 大寒 1月20日 11:08:58";

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日HH:mm:ss");
    public static void main(String[] args) {
        List<Solar> list = new LinkedList();
        String[] split = s.split(" ");

        for (int i = 0; i < split.length;) {
            if (i > 63){
                list.add(new Solar(split[i++], getDate("2019年"+split[i++]+split[i++])));
            }else {
                list.add(new Solar(split[i++], getDate("2018年"+split[i++]+split[i++])));
            }
        }

        Solar pre = null;
        try {
            pre = new Solar("大寒", sdf.parse("2018年1月20日 05:23:33"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        double total = 0;
        for (int i = 0; i < list.size() ; i++) {
            Solar solar = list.get(i);
            System.out.print(solar);
            System.out.print("\t");
            double dateDiff = getDateDiff(pre, solar);
            total += dateDiff;
            System.out.print(dateDiff);
            System.out.println();
            pre = solar;
        }
        System.out.println(total);
    }

    private static double getDateDiff(Solar pre, Solar solar) {
        if (pre==null){
            return 0;
        }
        long diff = solar.getDate().getTime() - pre.getDate().getTime();
        double d = diff / (24 * 60 * 60 * 1000D);
        return d;
    }


    private  static Date getDate(String str){
        try {
           return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}



class Solar{
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String name;
    private Date date;

    public Solar(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Solar{" +
                "name='" + name + '\'' +
                ", date=" + sdf.format(date) +
                '}';
    }
}
