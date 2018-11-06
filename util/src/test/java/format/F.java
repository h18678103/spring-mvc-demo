package format;

/**
 * @author huqinsong
 * @date 2018/9/18
 */
public class F {
    public static void main(String[] args) {
        String format = String.format("%s.ac-%d.%d.%d", "a", 1, 2, 3);
        System.out.println(format);
        String s = String.format("%b-%d-%x-%o-", 15, 15, 15, 15);
        System.out.println(s);
    }
}
