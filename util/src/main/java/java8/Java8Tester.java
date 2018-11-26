package java8;

import java.util.Comparator;

/**
 * @author huqinsong
 * @date 2018/11/13
 */
public class Java8Tester {
    public static void main(String args[]) {
        int num = 1;
        Converter<Integer, String> s = (param) -> System.out.println(String.valueOf(param + num));
        // 输出结果为 3
        s.convert(2);
//        num = 2;
        System.out.println(num);

//        String first = "";
        //编译会出错
        Comparator<String> comparator0 = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() < o2.length() ? -1 : o1.length() == o2.length() ? 0 : 1;
            }
        };
        Comparator<String> comparator1 = (first, second) -> Integer.compare(first.length(), second.length());
        Comparator<String> comparator2 = Comparator.comparingInt(String::length);
    }

    public interface Converter<T1, T2> {
        void convert(int i);
    }
}
