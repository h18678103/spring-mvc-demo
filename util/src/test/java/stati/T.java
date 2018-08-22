package stati;

import java.util.HashSet;
import java.util.Set;

/**
 * @author huqinsong
 * @date 2018/8/7
 */
public class T {

    public static class A {
        public Set<Integer> s1 = new HashSet<>();

    }
    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new A();

        a1.s1.add(1);
        a1.s1.add(2);

        System.out.println(a2.s1.size());
    }
}
