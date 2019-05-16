package exted;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author huqinsong
 * @date 2019/3/27
 */
public class A22 extends A2{
    @Override
    protected int AGet() {
//        super.AGet();
        System.out.println("A22");
        return 22;
    }
    public static void main(String[] args) {
        A a = new A22();
        System.out.println(a.get());

        String  s = "%3A";
        try {
            System.out.println(URLDecoder.decode(s, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
