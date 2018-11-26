package java8;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huqinsong
 * @date 2018/11/13
 */
public class Java8Tester2 {
    public static void main(String args[]){
        List names = new ArrayList();

        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add(null);
        names.add("Sina");

        names.forEach(System.out::println);
    }
}
