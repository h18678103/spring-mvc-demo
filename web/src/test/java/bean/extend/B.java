package bean.extend;

/**
 * @author huqinsong
 * @date 2019/2/21
 */
public class B extends A{

    @Override
    public void p(){
        System.out.println(1);
        super.p();
        System.out.println(2);
        System.out.println("p_B");
    }


    protected void p1(){
        System.out.println("p1_B");
    }

    @Override
    public void p2(){
        System.out.println("p2_B");
    }
}
