package map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author huqinsong
 * @date 2019/3/19
 */
public class MapTest {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();

        map.put("x", "12");
        String s1 = map.computeIfPresent("x",  (k, v)-> {
            System.out.println(k+"="+v);
            return "abc";});
        System.out.println(s1);
        System.out.println(map.get("x"));
        try {
        }catch (Exception e){
            e.printStackTrace();
        }
        String s2 = map.computeIfPresent("x", (k, v)-> {
            System.out.println(k+"="+v);
            return null;});
        System.out.println(s2);
        System.out.println(map.get("x"));


    }
}
