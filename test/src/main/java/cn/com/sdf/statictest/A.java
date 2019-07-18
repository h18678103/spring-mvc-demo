package cn.com.sdf.statictest;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author huqinsong
 * @date 2019/5/17
 */
@XmlRootElement(name= "xml")
public class A {

    private String ex;

    private static A a;

    public A() {
        System.out.println("A init");
        a = this;
    }

    public static A inst(){
        return a;
    }

    public String getEx() {
        return ex;
    }

    public void setEx(String ex) {
        this.ex = ex;
    }

    @Override
    public String toString() {
        return "A{" +
                "ex='" + ex + '\'' +
                '}';
    }
}
