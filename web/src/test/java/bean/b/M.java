package bean.b;

/**
 * @author huqinsong
 * @date 2019/2/21
 */
public abstract class M {


    public void m(){
        m1();
        System.out.println(1);
        m2();
    }

    protected abstract void m2();

    protected void m1(){
        System.out.println("M_m1");
    }
}
