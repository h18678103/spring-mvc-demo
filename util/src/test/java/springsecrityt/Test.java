package springsecrityt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author huqinsong
 * @date 2018/5/23
 */
public class Test {
    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public static void main(String[] args) {
        int t = 0;
        String password = "123456";
        System.out.println(password + " -> ");
        for (t = 1; t <= 10; t++) {
            String hashedPassword = passwordEncoder.encode(password);
            System.out.println(hashedPassword);
        }

        password = "MIKE123";
        System.out.println(password + " -> ");
        for (t = 1; t <= 10; t++) {
            String hashedPassword = passwordEncoder.encode(password);
            System.out.println(hashedPassword);
        }
        boolean matches = passwordEncoder.matches("123456", "$2a$10$ph/Shx8wV9SWHIihEGc6E.2coZaIwHwoB1yBjTnatfW5BCZzG2JiW");
        System.out.println(matches);
    }
}
