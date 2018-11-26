package java8;

/**
 * @author huqinsong
 * @date 2018/11/26
 */
public class CPU {

    public static void main(String[] args) {
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println(processors);
    }
}
