package old;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author huqinsong
 * @date 2018/9/13
 */
public class PrintSt {

    public static String printStackTraceToString(Throwable e) {
        try (StringWriter sw = new StringWriter()){
            e.printStackTrace(new PrintWriter(sw, true));
            return sw.getBuffer().toString();
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
    }


    public static void main(String[] args) {
        String s = null;
        try {
            s.toUpperCase();
        } catch (Exception e){
            String s1 = printStackTraceToString(e);
            System.out.println(s1);
            System.out.println("====================");
            e.printStackTrace();
        }
    }
}
