package init;

/**
 * @author huqinsong
 * @date 2018/5/30
 */
public class SSClass {
    static {
        System.out.println("SSClass");
    }

    {
        System.out.println("init SSClass A");
    }

    public SSClass() {
        System.out.println("init SSClass B");
    }
}

class SuperClass extends SSClass {
    static {
        System.out.println("SuperClass init!");
    }

    {
        System.out.println("init SuperClass A");
    }

    public static int value = 123;

    public SuperClass() {
        System.out.println("init SuperClass B");
    }
}

class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init");
    }

    {
        System.out.println("init SubClass A");
    }
    static int a;

    public SubClass() {
        System.out.println("init SubClass B");
    }
}

class NotInitialization {

    static
    {
        i=10010;
//        System.out.println(i);//这句编译器会报错：Cannot reference a field before it is defined（非法向前应用）
    }
    static int i;

    public static void main(String[] args) {
//        System.out.println(new SubClass());
//        System.out.println(SubClass.a);
        System.out.println(i);
//        System.out.println(SubClass.value);
    }
}
