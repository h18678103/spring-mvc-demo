package extend;

import java.util.Arrays;

/**
 * @author huqinsong
 * @date 2018/6/27
 */
public class M {
    public static void main(String[] args) {
        A1 a1 = new A1();
        A2 a2 = new A2();
        a1.getList().add("a");
        a1.getList().add("b");
        a2.getList().add("c");
        String[] arr = new String[5];
        a1.getList().toArray(arr);
        System.out.println(Arrays.toString(arr));
        String s2 = String.join(",", a2.getList());
        System.out.println(s2);

    }
}
