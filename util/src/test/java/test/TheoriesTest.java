package test;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * @author huqinsong
 * @date 2018/5/7
 */
@RunWith(Theories.class)
public class TheoriesTest {

    @DataPoint
    public static String nameValue1 = "Tony";
    @DataPoint
    public static String nameValue2 = "Jim";
    @DataPoint
    public static int ageValue1 = 10;
    @DataPoint
    public static int ageValue2 = 20;
    @DataPoint
    public static Long ageValue3 = 30l;
    @Theory
    public void testMethod(String name, int age){
        System.out.println(String.format("%s's age is %s", name, age));
    }
}
