package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author huqinsong
 * @date 2018/11/12
 */
public class XmlTest {

    public static void main(String[] args) {
        String path = XmlTest.class.getClassLoader().getResource("").getPath();
        System.out.println("path="+path);
        ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:spring.xml");
        System.out.println("ac="+ac);

    }

}
