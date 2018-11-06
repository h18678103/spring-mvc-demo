package word;

/**
 * @author huqinsong
 * @date 2018/9/28
 */
public class TestMain {
    public static void main(String[] args) {
        TestMain t = new TestMain();

        System.out.println(t.getClass());
        System.out.println(t.getClass().getClassLoader());
        System.out.println(t.getClass().getClassLoader().getResource("C.class").getPath());
        System.out.println(t.getClass().getClassLoader().getResource("/"));//null

        System.out.println(t.getClass().getClassLoader().getResource("").getPath());

        System.out.println(t.getClass().getClassLoader().getResource("1.properties"));
        System.out.println(t.getClass().getClassLoader().getResource("testpackage/2.properties"));
        System.out.println(t.getClass().getClassLoader().getResource("testpackage/subpackage/3.properties"));
    }
}
