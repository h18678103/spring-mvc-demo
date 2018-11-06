package extend;

/**
 * @author huqinsong
 * @date 2018/10/18
 */
public class C3 extends C2{


    protected void p(){
        System.out.println("c3");
    }


    public static void main(String[] args) {
        C1 c1 = new C3();
        c1.p();
    }
}
