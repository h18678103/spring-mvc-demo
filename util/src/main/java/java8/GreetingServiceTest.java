package java8;

/**
 * @author huqinsong
 * @date 2018/11/13
 */
public class GreetingServiceTest {
    public static void main(String[] args) {
        GreetingService greetService1 = message -> System.out.println("Hello " + message);
        greetService1.sayMessage("abc");
    }
}
