package designer23.structure.adapter;

/**
 * 适配器模式
 * @author huqinsong
 * @date 2018/12/12
 */
class Source {
    public void method1() {
        System.out.println("this is original method!");
    }
}

interface Targetable {
    /* 与原类中的方法相同 */
    void method1();

    /* 新类的方法 */
    void method2();
}

/**
 * 1、类的适配器模式
 */
public class Adapter extends Source implements Targetable {
    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }

    public static void main(String[] args) {
        Targetable source1 = new SourceSub1();
        Targetable source2 = new SourceSub2();

        source1.method1();
        source1.method2();
        source2.method1();
        source2.method2();
    }
}

/**
 * 2、对象的适配器模式
 */
class Wrapper implements Targetable {

    private Source source;

    public Wrapper(Source source){
        super();
        this.source = source;
    }
    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }

    @Override
    public void method1() {
        source.method1();
    }
}

/**
 * 3、接口的适配器模式
 */
abstract class Wrapper2 implements Targetable{
    @Override
    public void method1(){
        System.out.println("Wrapper2 method1");
    }
    @Override
    public void method2(){
        System.out.println("Wrapper2 method2");
    }
}

class SourceSub1 extends Wrapper2 {
    @Override
    public void method1(){
        System.out.println("the sourceable interface's first Sub1!");
    }
}
class SourceSub2 extends Wrapper2 {
    @Override
    public void method2(){
        System.out.println("the sourceable interface's second Sub2!");
    }
}