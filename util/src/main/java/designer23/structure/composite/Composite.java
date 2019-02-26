package designer23.structure.composite;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 组合模式（Composite）
 * @author huqinsong
 * @date 2018/12/12
 */
public class Composite {

    public static void main(String[] args) throws UnsupportedEncodingException {

        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(1544435004000L);
        System.out.println(instance);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        String format = sdf.format(instance.getTime());
        System.out.println(format);

        String s = "%B2%D9%D7%F7%B3%C9%B9%A6";
        System.out.println(URLDecoder.decode(s, "GBK"));

        String ss = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAK+LzCZnUWIsRSxK\n" +
                "yGZrZI+BU+Y+wnTXPpVbKcm5LT1fg/+o7aQR6B7pheWSEH5xLiFmtUkWSgZ7tYJh\n" +
                "jovJkwgIJ91BQBg3rVT3xPCjeVu88mrdvzQOe6sS5WNPu3Wxbht9uACO16zupdDr\n" +
                "ruhjRUaCX5tkLukccU3bqp9FpkkNAgMBAAECgYBx8mB1nSLqgqnz8ibatGL185Cu\n" +
                "J5a5mO36rM4XLqf66oEX9mMq2KS/S/2p4oHqUTUMYUrTQjCSvMI4+3I3soRI4k4J\n" +
                "5VsyP9zHyHzafvNUTUyp2ybaVgmh3oxU4sx015fd+3Qc219l+Jdod+rIi68NJqhh\n" +
                "MUU+q7yxmesCUCkZAQJBAOWH5bu9FmFIiSjWHVj6XE0904KOWSoHsenymzMZfM0s\n" +
                "1kck1hUvwntUcmUhkiuz4BBmiKOy65MtNyJ6ChE3UP0CQQDDyi/gX/xOhCOpWoDM\n" +
                "nYyKGyQH7GMJBIwK/X80Yha3Qtl/WrdqrpNV/ZHyQJgcIQFoMNLbNotoUOMAjthk\n" +
                "rR1RAkAU5RAmzQnShVXnH8bAKNpqNayhf+/iAZ1SnMFAH5va2bAP/ex3NUfRDljz\n" +
                "l+DElbVaCNt7e3gyh7UzMETmWFDJAkAwFtw1jz3ohxo/QYR7PYNEdLAf5hbZIy3G\n" +
                "kUcKNcGAl8HWPxDn+iMkLtkHGIiD+DNhRQS1ZStOnvdyrqNF7yNRAkEAxm2MZmPH\n" +
                "l+7jbDjHG6c+3SE6e0s7iZyatgh2gosKXdpqUWe3zVXPN04kLarZ7tasl1IBqHr1\n" +
                "LpzdHEUReiNRBQ==";
        System.out.println(ss.length());
    }
}
