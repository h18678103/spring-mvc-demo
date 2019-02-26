package bean.extend;

/**
 * @author huqinsong
 * @date 2019/2/21
 */
public class O<X extends A> {

    public void pp(X x){
        x.p();
        x.p2();
    }
}
