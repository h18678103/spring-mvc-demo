/**
 * @author huqinsong
 * @date 2018/3/22
 */
public class B extends A{
    static {
        System.out.println("b.statis{}");
    }
    {
        System.out.println("b.{}");
        System.out.println("b1===>"+this.getClass().getName());
    }
    public B() {
        System.out.println("b.cons");
        System.out.println("b2===>"+this.getClass().getName());
    }
}
