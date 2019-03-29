package exted;

/**
 * @author huqinsong
 * @date 2019/3/27
 */
public class A11 extends A1{

    public int getSupper(){
        return get();
    }

    public static void main(String[] args) {
        A11 a = new A11();
        int supper = a.getSupper();
        System.out.println(supper);
    }

}
