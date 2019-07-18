package cn.com.sdf.statictest;

/**
 * @author huqinsong
 * @date 2019/6/27
 */
public class Person {

    private String name;
    private String pwd;
    private int age;

    public Person(String name, String pwd, int age) {
        this.name = name;
        this.pwd = pwd;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
