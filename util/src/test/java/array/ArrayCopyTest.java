package array;

/**
 * @author huqinsong
 * @date 2018/7/25
 */
public class ArrayCopyTest {

    public static void main(String[] args) {
        byte[] srcBytes = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};  // 源数组

        byte[] destBytes = new byte[5]; // 目标数组

        System.arraycopy(srcBytes, 1, srcBytes, 0, 5);

        for (int i = 0; i < srcBytes.length; i++) {
            System.out.print("\t" + srcBytes[i]);
        }

        double i1 =5;
        int i2 =2;
        double d1 = i1/i2;
        System.out.println();
        System.out.println(d1);
    }
}
