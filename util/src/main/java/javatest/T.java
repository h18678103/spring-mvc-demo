package javatest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huqinsong
 * @date 2018/11/30
 */
public class T {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
                String mch_id = "test";
        switch (mch_id) {
            case "test":
                map.put("available_balance", 242101326450L);
                map.put("d0_balance", 2137390L);
                break;
            case "test01":
                map.put("available_balance", 18000);
                map.put("d0_balance", 1800);
                break;
            case "test02":
                map.put("available_balance", 19000);
                map.put("d0_balance", 1900);
                break;
            default:
                map.put("available_balance", 10000);
                map.put("d0_balance", 1000);
                break;
        }
        System.out.println(map);
    }
}
