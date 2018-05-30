import java.util.HashMap;
import java.util.Map;

/**
 * @author huqinsong
 * @date 2018/5/22
 */
public class MapTest {
    public static void main(String[] args) {
        Map<String, Object> m = new HashMap<>();
        m.put(null, "abc");
        System.out.println(m.containsKey(null));
        System.out.println(m.get(null));
    }
}
