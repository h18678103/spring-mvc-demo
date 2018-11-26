package old;

/**
 * @author huqinsong
 * @date 2018/8/24
 */
public class Tuofeng {

    public static void main(String[] args) {
        String s = "AHelloAppleRed";
        String s1 = camelCaseName2(s);
        System.out.println(s1);
    }

    public static String camelCaseName(String underscoreName) {
        StringBuilder result = new StringBuilder();
        if (underscoreName != null && underscoreName.length() > 0) {
            boolean flag = false;
            for (int i = 0; i < underscoreName.length(); i++) {
                char ch = underscoreName.charAt(i);
                if (i==0 && "_".charAt(0) != ch){
                    result.append(Character.toUpperCase(ch));
                    continue;
                }
                if ("_".charAt(0) == ch) {
                    flag = true;
                } else {
                    if (flag) {
                        result.append(Character.toUpperCase(ch));
                        flag = false;
                    } else {
                        result.append(ch);
                    }
                }
            }
        }
        return result.toString();
    }


    public static String camelCaseName2(String underscoreName) {
        StringBuilder result = new StringBuilder();
        if (underscoreName != null && underscoreName.length() > 0) {
            for (int i = 0; i < underscoreName.length(); i++) {
                char ch = underscoreName.charAt(i);
                if (i > 0 && Character.isUpperCase(ch)){
                    result.append(" ").append(ch);
                }else {
                    result.append(ch);
                }
            }
        }
        return result.toString();
    }
}
