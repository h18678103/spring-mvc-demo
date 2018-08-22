package xml;


import com.alibaba.fastjson.JSONObject;

/**
 * @author huqinsong
 * @date 2018/8/15
 */
public class Test1 {

    public static void main(String[] args) {
        JSONObject j = new JSONObject();
        j.put("bb", "bbx");
        System.out.println(j.put("bb", "bbb"));
    }
}
