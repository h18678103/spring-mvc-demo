package extend;

/**
 * @author huqinsong
 * @date 2018/6/27
 */
public class A1 extends A{

    @Override
    public void a() throws NullPointerException {
        try {
            super.a();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void b(){
        System.out.println("A1b");
    }
}
