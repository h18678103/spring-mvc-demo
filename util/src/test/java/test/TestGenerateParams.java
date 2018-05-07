package test;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * @author huqinsong
 * @date 2018/5/7
 */
@RunWith(Parameterized.class)
public class TestGenerateParams {
    private String greeting;

    public TestGenerateParams(String greeting) {
        super();
        this.greeting = greeting;
    }

    @Test
    public void testParams() {
        System.out.println(greeting);
    }

    /**
     * 这里的返回的应该是一个可迭代数组，且方法必须是public static
     *
     * @return
     */
    @Parameterized.Parameters
    public static List getParams() {
        return Arrays.asList(new String[][]{{"hello"}, {"hi"}, {"good morning"}, {"how are you"}});
    }
}
