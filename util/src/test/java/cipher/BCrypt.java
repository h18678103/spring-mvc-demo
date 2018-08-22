package cipher;

import java.util.HashSet;
import java.util.Set;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author huqinsong
 * @date 2018/7/25
 */
public class BCrypt {


    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public static void main(String[] args) {
//        Set<String> set = new HashSet<>(100);

        for (int i = 0; i<10; i++){
            String encode = passwordEncoder.encode("123456");
            System.out.println(encode.length());
            boolean matches = passwordEncoder.matches("123456", encode);
            System.out.println(matches);
        }
    }
}
