package bigdecmali;

import java.math.BigDecimal;

/**
 * @author huqinsong
 * @date 2018/8/19
 */
public class T {

    /** 100 */
    private static final BigDecimal DEC100 = new BigDecimal(100);

    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal(125005).divide(DEC100);
        System.out.println(bigDecimal+"元");
    }
}
