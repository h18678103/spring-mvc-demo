package java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author huqinsong
 * @date 2018/6/27
 */
public class TestShuangMaohao {

    public static void  printValur(String str){
        System.out.println("print value : "+str);
    }

    public static void  printValur2(String str){
        System.out.println("2print value : "+str);
    }

    public static void main(String[] args) {
        List<String> al = Arrays.asList("a","b","c","d");
//        for (String a: al) {
//            TestShuangMaohao.printValur(a);
//        }
        //下面的for each循环和上面的循环是等价的
//        al.forEach(x-> TestShuangMaohao.printValur(x));

//        al.forEach(TestShuangMaohao::printValur);
        //下面的方法和上面等价的
        Consumer<String> methodParam = TestShuangMaohao::printValur2; //方法参数
//        al.forEach(x -> methodParam.accept(x));//方法执行accept
        al.forEach(methodParam);//方法执行accept
    }
}
