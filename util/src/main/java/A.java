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
    }
    public A() {
        System.out.println("a.cons");
    }
}
