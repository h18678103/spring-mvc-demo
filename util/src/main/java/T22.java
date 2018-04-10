import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Set;

/**
 * @author huqinsong
 * @date 2018/3/27
 */
public class T22 {
    public static void main(String[] args) {
        Set<Short> s = new HashSet<>();
        Short s1 = 1;
        s.add(s1);
        s1=2;
        s.add(s1);
        s1=3;
        s.add(s1);
        Short[] arr = new Short[0];
        arr = s.toArray(arr);
        System.out.println(arr[0]);
        System.out.println(arr[1]);
        System.out.println(arr[2]);
    }
}
