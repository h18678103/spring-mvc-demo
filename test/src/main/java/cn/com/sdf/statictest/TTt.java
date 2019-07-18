package cn.com.sdf.statictest;

/**
 * @author huqinsong
 * @date 2019/5/21
 */
public class TTt {
    public static void main(String[] args) {
        int n = 0;
        do {
            n++;
            System.out.println("A===>"+n);
            if (n % 2 == 0) {
                break;
            }
            System.out.println("B===>"+n);
        } while (n < 10);
    }
}
