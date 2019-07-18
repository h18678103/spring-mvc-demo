package cn.com.sdf.statc;

/**
 * @author huqinsong
 * @date 2019/6/21
 */
public class B extends A {

    @Override
    public boolean isT(){
        return true;
    }

    public static void main(String[] args) {
        A b = new B();
        b.test();
    }

}
