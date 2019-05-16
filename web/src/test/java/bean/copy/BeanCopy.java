package bean.copy;

import org.springframework.beans.BeanUtils;

/**
 * @author huqinsong
 * @date 2019/5/16
 */
public class BeanCopy {

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        BeanUtils.copyProperties(a, b);
        System.out.println(a);
        System.out.println(b);
    }

}
