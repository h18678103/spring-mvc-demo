package excel;

/**
 * @author huqinsong
 * @date 2018/8/22
 */
public class T {
    public static void main(String[] args) {
        String s = " a b  c    d ";
        System.out.println(s.replaceAll("\\s+", "_"));
    }
}
