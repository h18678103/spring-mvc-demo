package java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author huqinsong
 * @date 2018/11/13
 */
public class StreamTEst {
    public static void main(String[] args) {
        peek();
    }

    public static void summaryStatistics(){
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
    }

    public static void stream(){
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl", null);
        List<String> filtered = strings.stream().filter(string -> string != null && !string.isEmpty()).collect(Collectors.toList());
        System.out.println(filtered);
        String mergedString = strings.stream().filter(string -> string != null && !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);
        // 获取空字符串的数量
        long count = strings.parallelStream().filter(string -> string == null || string.isEmpty()).count();
        System.out.println(count);
    }

    public static void forEach(){
        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);
    }

    public static void map(){
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数
        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
    }

    public static void filter(){
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        // 获取空字符串的数量
        long count = strings.stream().filter(string -> string.isEmpty()).count();
    }

    public static void peek(){
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
    }
}
