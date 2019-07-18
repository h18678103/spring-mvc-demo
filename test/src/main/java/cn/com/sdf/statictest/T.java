package cn.com.sdf.statictest;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * @author huqinsong
 * @date 2019/5/17
 */
public class T {


    public static void main(String[] args) throws JAXBException {
        test2();
        test();
        test3();
    }

    private static void test2() {
        A inst = A.inst();
        System.out.println(inst);
    }

    private static void test3() {
        A inst =  A.inst();
        System.out.println(inst);
    }

    static void test() throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(A.class);
        Unmarshaller u = jc.createUnmarshaller();
        u.unmarshal(new File("C:\\Users\\user003\\Desktop\\a.xml"));
//        try {
//            A a = A.class.newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
    }
}
