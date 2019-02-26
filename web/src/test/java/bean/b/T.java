package bean.b;

import org.omg.CORBA.Object;

/**
 * @author huqinsong
 * @date 2019/2/21
 */
public class T {

    public static void main(String[] args) {
        AM m = new AM();
        m.m();
        T t = new T();
        T s = t.getS();
    }


    private <S> S getS(){
        return (S) new AM();
    }
}
