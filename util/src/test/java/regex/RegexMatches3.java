package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author huqinsong
 * @date 2018/5/3
 */
public class RegexMatches3 {
    public static void main( String args[] ){
        Pattern pattern = Pattern.compile("foo");
        Matcher matcher = pattern.matcher("fooooooooooooooooo");
        Matcher matcher2 = pattern.matcher("ooooofoooooooooooo");

        System.out.println("lookingAt(): "+matcher.lookingAt());
        System.out.println("matches(): "+matcher.matches());
        System.out.println("lookingAt(): "+matcher2.lookingAt());
    }
}
