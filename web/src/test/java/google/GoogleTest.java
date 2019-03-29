package google;

import com.warrenstrange.googleauth.GoogleAuthenticator;

/**
 * https://d2.fptrade.show
 *
 * @author huqinsong
 * @date 2019/3/20
 */
public class GoogleTest {

    private static final String secretKey = "2YOUU6R7QPLL4JLI";

    public static void main(String[] args) {
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        int password = gAuth.getTotpPassword(secretKey);
        System.out.println(password);
        boolean isCodeValid = gAuth.authorize(secretKey, password);
        System.out.println(isCodeValid);
    }

}
