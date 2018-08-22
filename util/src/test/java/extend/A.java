package extend;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author huqinsong
 * @date 2018/6/27
 */
public abstract class A {

    public void test() {
        int i  =  10;
        i = 2 ;

    }


    private final List<String> list = new ArrayList<>();

    public List<String> getList() {
        return list;
    }


    public void a() throws Exception{
        System.out.println("Aa");
        b();
    }

    public void b(){
        System.out.println("Ab");
    }


    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
    }


}
