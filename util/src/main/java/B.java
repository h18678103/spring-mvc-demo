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
    }
    public B() {
        System.out.println("b.cons");
    }
}
