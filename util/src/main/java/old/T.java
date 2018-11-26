package old;

/**
 * @author huqinsong
 * @date 2018/3/15
 */
public class T {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

//        Class<?> b = Class.forName("B");
//        ClassLoader loader = T.class.getClassLoader();
//        Class<?> b = loader.loadClass("B");
//        Object o = b.newInstance();
//        A a= new B();
//        System.out.println(a);
        long s1 = 0b1100;

        long s2 = 0b1100;
        long l = s1 + s2;
        char a1 = (char) -97;
        System.out.println("====>"+a1);
    }
}
