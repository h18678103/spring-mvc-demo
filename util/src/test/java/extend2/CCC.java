package extend2;

import java.math.BigDecimal;

public class CCC {
    static BigDecimal ne = new BigDecimal(-1);
    static BigDecimal zero = new BigDecimal(0);
    static BigDecimal one = new BigDecimal(1);
    static BigDecimal m = new BigDecimal(6);
    static BigDecimal n = new BigDecimal(7);
//    static BigDecimal x = new BigDecimal(16);


    public static void main(String[] args) {
        BigDecimal x1 = factorialRecursive(n);
        BigDecimal x2 = factorialRecursive(m).multiply(factorialRecursive(n.subtract(m)));
        System.out.println(x1.divide(x2));
    }

    public static BigDecimal factorialRecursive(BigDecimal n) {
        // 阶乘对整数才有意义
        if (n.compareTo(zero) < 0) {
            return ne;
        }
        // 0！=1，（0 的阶乘是存在的）
        if (n.compareTo(zero) == 0) {
            return one;
        }
        return n.multiply(factorialRecursive(n.subtract(one))) ;
    }
}
