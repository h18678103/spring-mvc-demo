/**
 * @author huqinsong
 * @date 2018/3/22
 */
public class A {
    static {
        System.out.println("a.statis{}");
    }
    {
        System.out.println("a.{}");
        System.out.println("a1===>"+this.getClass().getName());
    }
    public A() {
        System.out.println("a.cons");
        System.out.println("a2===>"+this.getClass().getName());
    }
}
