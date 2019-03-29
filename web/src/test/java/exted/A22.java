package exted;

/**
 * @author huqinsong
 * @date 2019/3/27
 */
public class A22 extends A1{

    public int getSupper(){
        return get();
    }

    public static void main(String[] args) {
        A22 a = new A22();
        int supper = a.getSupper();
        System.out.println(supper);
    }

}
