package thread;

/**
 * @author huqinsong
 * @date 2018/7/5
 */
public class T {
    public static void main(String[] args) {
        Byte b = new Byte((byte) 1);
        boolean b1 = b instanceof Number;
        System.out.println(b1);
        Byte i=127;
        boolean equals = b == i;
        System.out.println(equals);
        System.out.println(b.equals(i));
    }
}
