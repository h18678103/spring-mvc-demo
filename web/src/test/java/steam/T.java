package steam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;

/**
 * @author huqinsong
 * @date 2019/3/20
 */
public class T {

    private List<Student> stuList;

    @Before
    public void init() {
        Random random = new Random();
        stuList = new ArrayList() {
            {
                for (int i = 0; i < 100; i++) {
                    add(new Student("student" + i, random.nextInt(50) + 50));
                }
            }
        };
    }

    //1列出班上超过85分的学生姓名，并按照分数降序输出用户名字
    @Test
    public void test() {
        List<String> studentList = stuList.stream()
                .filter(x->x.getScore()>85)
                .sorted(Comparator.comparing(Student::getScore).reversed())
                .map(Student::toString)
                .collect(Collectors.toList());
        Stream<String> stream = studentList.stream();
        stream.forEach(System.out::println);
    }

    @Test
    public void test1(){
        Stream<String> stream = Stream.generate(()->"user").limit(5);
        stream.forEach(System.out::println);
    }


    /**
     * 我们将filter中的逻辑抽象成方法，在方法中加入打印逻辑，如果stream的转换操作是延迟执行的，那么split会先打印，否则后打印，代码运行结果为
     */
    @Test
    public void test3() {
        Stream<Student> stream = Stream.of(stuList.get(0)).filter(this::filter);
        System.out.println("split-------------------------------------");
        List<Student> studentList = stream.collect(Collectors.toList());
    }



    public boolean filter(Student s) {
        System.out.println("begin compare");
        return s.getScore() > 85;
    }

    private boolean filter(List<Student> students) {
        return false;
    }

}
