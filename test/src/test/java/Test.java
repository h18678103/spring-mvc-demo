import com.alibaba.fastjson.JSON;

/**
 * @author huqinsong
 * @date 2019/5/14
 */
public class Test {

    public static void main(String[] args) {
        Obj obj = new Obj();
        obj.setS("sss123");
        Obj obj2 = new Obj();
        obj2.setS("sss123456");
        obj.setObj(null);
        String s = JSON.toJSONString(obj);
        System.out.println(s);
    }
}
