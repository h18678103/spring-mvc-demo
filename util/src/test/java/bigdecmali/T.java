package bigdecmali;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author huqinsong
 * @date 2018/8/19
 */
public class T {

    /** 100 */
    private static final BigDecimal DEC100 = new BigDecimal(100);

    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal(0.001998765);
        DecimalFormat df = new DecimalFormat("#.####%");
        System.out.println(df.format(bigDecimal)+"元");
    }
}
