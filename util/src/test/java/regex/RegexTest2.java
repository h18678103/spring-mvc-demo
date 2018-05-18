package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * lookAt()
 * find()
 * matches()的区别
 */
public class RegexTest2 {
    public static void main(String[] args){
        test();
    }

    public static void test() {
        Pattern pattern = Pattern.compile("\\d{3,5}");
        String charSequence = "123-34345-234-00";
        Matcher matcher = pattern.matcher(charSequence);

        //虽然匹配失败，但由于charSequence里面的"123"和pattern是匹配的,所以下次的匹配从位置4开始
        print("1matcher.matches()=>"+matcher.matches());
        //测试匹配位置
        matcher.find();
        print("2matcher.start()=>"+matcher.group()+" - "+matcher.start());

        //使用reset方法重置匹配位置
        matcher.reset();

        //第一次find匹配以及匹配的目标和匹配的起始位置
        print("3matcher.find()=>"+matcher.find());
        print("4matcher.group()=>"+matcher.group()+" - "+matcher.start());
        //第二次find匹配以及匹配的目标和匹配的起始位置
        print("5matcher.find()=>"+matcher.find());
        print("6matcher.group()=>"+matcher.group()+" - "+matcher.start());

        //第一次lookingAt匹配以及匹配的目标和匹配的起始位置
        print("7matcher.lookingAt()=>"+matcher.lookingAt());
        print("8matcher.group()=>"+matcher.group()+" - "+matcher.start());

        //第二次lookingAt匹配以及匹配的目标和匹配的起始位置
        print("9matcher.lookingAt()=>"+matcher.lookingAt());
        print("0matcher.group()=>"+matcher.group()+" - "+matcher.start());
    }


    public static void print(Object o){
        System.out.println(o);
    }
}